package com.homework.message.queue.core.broker;

import lombok.Data;

/**
 * 消息体
 *
 * @author bob
 */
@Data
public class Message {

  private String topic;

  private String content;

  public Message(String topic, String content) {
    this.topic = topic;
    this.content = content;
  }
}
