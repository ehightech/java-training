package com.homework.spring.kafka.demo.controller;

import com.homework.spring.kafka.demo.configuration.KafkaProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bob
 */
@Log4j2
@RestController
@RequestMapping("kafka")
public class ProducerController {

  private final KafkaProperty kafkaProperty;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public ProducerController(
      KafkaProperty kafkaProperty,
      KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaProperty = kafkaProperty;
    this.kafkaTemplate = kafkaTemplate;
  }

  @RequestMapping("send")
  public String send(String name) {
    ListenableFuture<SendResult<String, String>> future =
        kafkaTemplate.send(kafkaProperty.getProducer().getTopicName(), name);
    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("生产者-发送消息成功：{}", result.toString());
      }

      @Override
      public void onFailure(Throwable throwable) {
        log.error("生产者-发送消息失败：{}", throwable.getMessage());
      }
    });

    return "success";
  }
}
