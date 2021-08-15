package com.homework.xa.transaction.aspect;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author bob
 */
@Log4j2
@ControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public String exceptionHandler(Exception ex) {
    log.error("exception happened: ", ex);
    return ex.getMessage();
  }
}
