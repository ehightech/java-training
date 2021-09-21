package com.homework.message.queue.core.producer;

/**
 * 消息生产者接口
 *
 * @author bob
 */
public interface Producer {

  /**
   * 发送消息
   *
   * @param topic   主题
   * @param message 消息内容
   * @return 发送结果, true: 成功, false: 失败
   */
  boolean send(String topic, String message);
}
