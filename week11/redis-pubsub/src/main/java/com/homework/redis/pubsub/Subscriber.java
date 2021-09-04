package com.homework.redis.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.redis.pubsub.dto.OrderInfoDTO;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.JedisPubSub;

/**
 * @author bob
 */
@Log4j2
public class Subscriber extends JedisPubSub {

  @Override
  public void onMessage(String channel, String message) {
    log.info("onMessage, channel: {}, message: {}", channel, message);
    try {
      OrderInfoDTO order = new ObjectMapper().readValue(message, OrderInfoDTO.class);
      log.info("order info: {}", order);
    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
    }
  }
}
