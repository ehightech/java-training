package com.homework.activemq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author bob
 */
@SpringBootApplication
@EnableJms
public class ActivemqDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActivemqDemoApplication.class, args);
  }

}
