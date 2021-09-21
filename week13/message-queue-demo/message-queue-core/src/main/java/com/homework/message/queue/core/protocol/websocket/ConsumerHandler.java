package com.homework.message.queue.core.protocol.websocket;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 消费者处理器
 *
 * @author bob
 */
@Log4j2
public class ConsumerHandler implements WebSocketHandler {

  @Override
  public void afterConnectionEstablished(WebSocketSession webSocketSession) {

  }

  @Override
  public void handleMessage(WebSocketSession webSocketSession,
      WebSocketMessage<?> webSocketMessage) {
    log.info(webSocketMessage.getPayload().toString());
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
