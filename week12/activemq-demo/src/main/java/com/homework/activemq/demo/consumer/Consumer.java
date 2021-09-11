package com.homework.activemq.demo.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@Log4j2
@Component
public class Consumer {

  @JmsListener(destination = "queue")
  public void listenQueue(String msg) {
    log.info("receive queue msg: {}", msg);
  }

  @JmsListener(destination = "topic")
  public void listenTopic(String msg) {
    log.info("receive topic msg: {}", msg);
  }
}
