package com.homework.message.queue.core.consumer;

import java.util.List;

/**
 * 消息消费者接口
 *
 * @author bob
 */
public interface Consumer {

  /**
   * 获取数据
   *
   * @param rate 最大数据量
   * @return 数据
   */
  List poll(int rate);
}
