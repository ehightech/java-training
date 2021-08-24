package com.homework.rpcfx.core.exception;

/**
 * @author bob
 */
public class RpcFxException extends Exception {

  public RpcFxException() {
  }

  public RpcFxException(String message) {
    super(message);
  }

  public RpcFxException(String message, Throwable cause) {
    super(message, cause);
  }
}
