package com.homework.message.queue.core.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * uuid工具类
 *
 * @author bob
 */
public class UUIDUtil {

  private static String localHost = null;

  public static String getUUID() {
    if (localHost == null) {
      localHost = getLocalHost();
    }

    return localHost + System.currentTimeMillis();
  }

  private static String getLocalHost() {
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    return null;
  }
}
