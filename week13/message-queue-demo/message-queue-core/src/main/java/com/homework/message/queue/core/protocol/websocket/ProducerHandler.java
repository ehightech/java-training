package com.homework.message.queue.core.protocol.websocket;

import com.google.gson.Gson;
import com.homework.message.queue.core.broker.MessageProcessor;
import com.homework.message.queue.core.common.Constant;
import java.util.Map;
import lombok.SneakyThrows;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 生产者处理器
 *
 * @author bob
 */
public class ProducerHandler implements WebSocketHandler {

  private final MessageProcessor broker;
  private Gson gson = new Gson();

  ProducerHandler(MessageProcessor broker) {
    this.broker = broker;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession webSocketSession) {
  }

  @SneakyThrows
  @Override
  public void handleMessage(WebSocketSession webSocketSession,
      WebSocketMessage<?> webSocketMessage) {
    String topic = Constant.TOPIC;
    String message = Constant.MESSAGE;

    Map map = gson.fromJson(webSocketMessage.getPayload().toString(), Map.class);
    if (broker.send(map.get(topic).toString(), map.get(message).toString())) {
      webSocketSession.sendMessage(new TextMessage(map.get(Constant.SEND_ID).toString()));
    } else {
      webSocketSession.sendMessage(new TextMessage("null"));
    }
  }

  @Override
  public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
  }

  @Override
  public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}
