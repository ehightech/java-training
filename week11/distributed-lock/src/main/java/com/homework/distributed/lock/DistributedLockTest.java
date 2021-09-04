package com.homework.distributed.lock;

import com.homework.distributed.lock.util.DistributedLock;
import com.homework.distributed.lock.util.impl.RedisDistributedLock;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;

/**
 * @author bob
 */
@Log4j2
public class DistributedLockTest {

  static int count = 10;

  public static void test() {
    log.info("count remain: {}", --count);
  }

  public static void main(String[] args) {
    Runnable runnable = () -> {
      DistributedLock lock = null;
      String identifier = null;
      try {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        lock = new RedisDistributedLock(jedis, "lock_key", 10 * 1000);
        identifier = lock.acquire();
        log.info("线程: {}正在运行", Thread.currentThread().getName());
        test();
      } finally {
        if (Objects.nonNull(lock)) {
          lock.release(identifier);
        }
      }
    };

    for (int i = 0; i < 10; i++) {
      Thread t = new Thread(runnable);
      t.start();
    }
  }
}
