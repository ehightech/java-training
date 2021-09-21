package com.homework.message.queue.core.producer;

import com.google.gson.Gson;
import com.homework.message.queue.core.common.Constant;
import com.homework.message.queue.core.common.UUIDUtil;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * websocket 消息生产者
 *
 * @author bob
 */
@Log4j2
public class WebsocketProducer implements Producer {

  private Map<String, Object> properties;
  private Map<String, Boolean> resultCache = new HashMap<>();
  private WebSocketClient client;
  private Gson gson = new Gson();

  public WebsocketProducer(Map<String, Object> properties) {
    this.properties = properties;
    connect();
  }

  @SneakyThrows
  private void connect() {
    client = new WebSocketClient(new URI(properties.get(Constant.URL).toString())) {
      @Override
      public void onOpen(ServerHandshake serverHandshake) {
        log.info("producer websocket client connected");
      }

      @Override
      public void onMessage(String s) {
        if (!"null".equals(s)) {
          resultCache.put(s, true);
        }
        notifyAll();
      }

      @Override
      public void onClose(int i, String s, boolean b) {
        log.info("producer websocket client close");
      }

      @Override
      public void onError(Exception e) {

      }
    };
    log.info(client.connectBlocking());
  }

  @SneakyThrows
  @Override
  public synchronized boolean send(String topic, String message) {
    String id = UUIDUtil.getUUID();
    String sendTimeout = Constant.SEND_TIMEOUT;
    int defaultTimeout = Constant.DEFAULT_SEND_TIMEOUT;
    long timeout = Long.parseLong(properties.getOrDefault(sendTimeout, defaultTimeout).toString());

    Map<String, Object> map = new HashMap<>(3);
    map.put(Constant.TOPIC, topic);
    map.put(Constant.MESSAGE, message);
    map.put(Constant.SEND_ID, id);

    client.send(gson.toJson(map));
    while (!resultCache.getOrDefault(id, false)) {
      this.wait(timeout);
    }
    if (!resultCache.getOrDefault(id, false)) {
      return false;
    }
    resultCache.remove(id);
    return true;
  }
}
