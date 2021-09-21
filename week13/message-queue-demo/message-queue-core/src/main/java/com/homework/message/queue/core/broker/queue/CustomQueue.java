package com.homework.message.queue.core.broker.queue;

import com.homework.message.queue.core.common.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义消息队列
 *
 * @author bob
 */
public class CustomQueue {

  /**
   * 各个分组的读取位置
   * <p>
   * group --> index
   */
  private Map<String, AtomicInteger> offset = new HashMap<>();

  /**
   * 写位置记录
   */
  private int writeIndex = 0;

  /**
   * 存储队列
   */
  private List<String> queue = new ArrayList<>();

  public void put(String message) {
    synchronized (Constant.WRITE_LOCK) {
      queue.add(message);
      writeIndex += 1;
    }
  }

  public String get(String group) {
    int index = offset.getOrDefault(group, new AtomicInteger(-1)).incrementAndGet();
    if (0 == writeIndex || index >= queue.size()) {
      return null;
    }
    return queue.get(index);
  }

  public boolean isEmpty() {
    return 0 == writeIndex || writeIndex >= queue.size();
  }

  public int size() {
    return writeIndex;
  }
}
