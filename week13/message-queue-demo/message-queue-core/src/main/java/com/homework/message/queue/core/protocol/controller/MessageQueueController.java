package com.homework.message.queue.core.protocol.controller;

import com.homework.message.queue.core.broker.Message;
import com.homework.message.queue.core.broker.MessageProcessor;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于API接口
 *
 * @author bob
 */
@RestController
public class MessageQueueController {

  private final MessageProcessor messageProcessor;

  public MessageQueueController(MessageProcessor broker) {
    this.messageProcessor = broker;
  }

  @PostMapping("/send")
  public boolean send(@RequestBody Message message) {
    return messageProcessor.send(message.getTopic(), message.getContent());
  }

  @GetMapping("/poll")
  public List poll(@RequestParam(value = "topic") String topic,
      @RequestParam(value = "rate") Integer rate,
      @RequestParam(value = "group") String group) {
    return messageProcessor.poll(topic, group, rate);
  }
}
