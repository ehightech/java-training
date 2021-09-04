package com.homework.distributed.lock.util;

/**
 * @author bob
 */
public interface DistributedLock {

  /**
   * 获取锁
   *
   * @return 锁标识
   */
  String acquire();

  /**
   * 释放锁
   *
   * @param identifier 锁标识
   * @return true: 成功, false: 失败
   */
  boolean release(String identifier);
}
