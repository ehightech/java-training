package com.homework.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * @author bob
 */
public class Publisher {

  private Jedis publisherJedis;
  private String channel;

  public Publisher(Jedis publisherJedis, String channel) {
    this.publisherJedis = publisherJedis;
    this.channel = channel;
  }

  public void startPublish(String message) {
    publisherJedis.publish(channel, message);
  }
}
