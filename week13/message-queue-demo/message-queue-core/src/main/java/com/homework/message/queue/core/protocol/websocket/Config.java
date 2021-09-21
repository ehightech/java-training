package com.homework.message.queue.core.protocol.websocket;

import com.homework.message.queue.core.broker.MessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * websocket 配置类
 *
 * @author bob
 */
@Configuration
@EnableWebSocket
public class Config implements WebSocketConfigurer {

  private final MessageProcessor broker;

  public Config(MessageProcessor broker) {
    this.broker = broker;
  }

  @Bean
  public WebSocketHandler producerHandler() {
    return new ProducerHandler(broker);
  }

  @Bean
  public WebSocketHandler consumerHandler() {
    return new ConsumerHandler();
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
    webSocketHandlerRegistry.addHandler(producerHandler(), "/producer")
        .addInterceptors(new HttpSessionHandshakeInterceptor())
        .setAllowedOrigins("*");

    webSocketHandlerRegistry.addHandler(producerHandler(), "/consumer")
        .addInterceptors(new HttpSessionHandshakeInterceptor())
        .setAllowedOrigins("*");
  }
}
