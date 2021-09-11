package com.homework.activemq.demo.config;

import javax.jms.Queue;
import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bob
 */
@Configuration
public class ActivemqConfig {

  @Bean
  public Queue queue() {
    return new ActiveMQQueue("queue");
  }

  @Bean
  public Topic topic() {
    return new ActiveMQTopic("topic");
  }
}
