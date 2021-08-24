package com.homework.rpcfx.core.exception;

import com.homework.rpcfx.core.api.RpcfxResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@Component
@Aspect
@Slf4j
public class GlobalExceptionHandler {

  @Pointcut("execution(* com.homework.rpcfx.proxy.*.*(..))")
  public void pointCut() {
  }

  @Around("pointCut()")
  public Object handlerException(ProceedingJoinPoint proceedingJoinPoint) {
    try {
      RpcfxResponse response = (RpcfxResponse) proceedingJoinPoint.proceed();
      if (!response.isStatus()) {
        response.setException(new RpcFxException("RPC exception happened"));
      }
      return response;
    } catch (Throwable ex) {
      log.error("execute rpc calling exception.", ex);
    }
    return null;
  }
}
