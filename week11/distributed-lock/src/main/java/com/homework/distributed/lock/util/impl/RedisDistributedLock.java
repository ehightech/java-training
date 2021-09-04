package com.homework.distributed.lock.util.impl;

import com.homework.distributed.lock.util.DistributedLock;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author bob
 */
@Log4j2
public class RedisDistributedLock implements DistributedLock {

  private static final String LOCK_SUCCESS = "OK";
  private static final Long RELEASE_SUCCESS = 1L;

  /**
   * 锁键值
   */
  String lockKey;

  /**
   * 锁的超时时间,单位：毫秒
   */
  int expireTime = 10 * 1000;

  /**
   * 获取锁超时时间,单位：毫秒
   */
  int acquireTimeout = 1 * 1000;

  /**
   * jedis客户端
   */
  private Jedis jedis;

  public RedisDistributedLock(Jedis jedis, String lockKey, int expireTime) {
    this.jedis = jedis;
    this.lockKey = lockKey;
    this.expireTime = expireTime;
  }

  public RedisDistributedLock(Jedis jedis, String lockKey, int acquireTimeout, int expireTime) {
    this.jedis = jedis;
    this.lockKey = lockKey;
    this.acquireTimeout = acquireTimeout;
    this.expireTime = expireTime;
  }

  @Override
  public String acquire() {
    try {
      long start = System.currentTimeMillis();
      long end = start + acquireTimeout;
      String requireToken = UUID.randomUUID().toString();
      while (start < end) {
        SetParams params = new SetParams();
        params.nx().px(expireTime);
        String result = jedis.set(lockKey, requireToken, params);
        if (LOCK_SUCCESS.equals(result)) {
          return requireToken;
        }
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        start = System.currentTimeMillis();
      }
    } catch (Exception ex) {
      log.error("acquire lock error", ex);
    }

    return null;
  }

  @Override
  public boolean release(String identifier) {
    if (Objects.isNull(identifier)) {
      return false;
    }

    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    Object result = new Object();
    try {
      result = jedis.eval(script, Collections.singletonList(lockKey),
          Collections.singletonList(identifier));
      if (RELEASE_SUCCESS.equals(result)) {
        log.info("release lock successfully, requiredToken: {}", identifier);
        return true;
      }
    } catch (Exception ex) {
      log.error("release lock error", ex);
    } finally {
      if (Objects.nonNull(jedis)) {
        jedis.close();
      }
    }

    log.info("release lock failure, requiredToken: {}, result: {}", identifier, result);
    return false;
  }
}
