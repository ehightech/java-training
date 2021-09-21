package com.homework.spring.kafka.demo.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@Component
public class KafkaConsumer {

  private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = {"my-topic"})
  public void receive(ConsumerRecord<?, ?> record) {
    logger.info("消费得到的消息---key: " + record.key());
    logger.info("消费得到的消息---value: " + record.value().toString());
  }
}
