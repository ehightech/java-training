package com.homework.redis.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.redis.pubsub.dto.OrderInfoDTO;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author bob
 */
@Log4j2
@Component
public class PubsubTest {

  public static final String CHANNEL = "pubsub_channel";

  private final static JedisPool JEDIS_POOL = new JedisPool();

  public static void main(String[] args) {
    final Jedis pubJedis = JEDIS_POOL.getResource();
    final Jedis subJedis = JEDIS_POOL.getResource();
    final Subscriber subscriber = new Subscriber();
    Runnable runnable = () -> {
      try {
        log.info("start subscribe");
        subJedis.subscribe(subscriber, CHANNEL);
        log.info("end subscribe");
      } catch (Exception e) {
        e.printStackTrace();
      }
    };
    Thread t = new Thread(runnable);
    t.start();

    for (int i = 0; i < 10; i++) {
      OrderInfoDTO order = new OrderInfoDTO();
      order.setId((long) (i+1));
      order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
      order.setContent("order" + (i + 1));
      order.setOrderTime(LocalDateTime.now());
      try {
        String message = new ObjectMapper().writeValueAsString(order);
        new Publisher(pubJedis, CHANNEL).startPublish(message);
      } catch (JsonProcessingException e) {
        log.error("JsonProcessingException", e);
      }
    }
    pubJedis.close();

    subscriber.unsubscribe(CHANNEL);
    subJedis.close();
  }
}
