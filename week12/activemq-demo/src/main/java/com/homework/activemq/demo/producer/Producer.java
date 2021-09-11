package com.homework.activemq.demo.producer;

import javax.jms.Queue;
import javax.jms.Topic;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bob
 */
@Log4j2
@RestController
@RequestMapping("test")
public class Producer {

  private final JmsMessagingTemplate jmsMessagingTemplate;
  private final Queue queue;
  private final Topic topic;

  @Autowired
  public Producer(JmsMessagingTemplate jmsMessagingTemplate, Queue queue, Topic topic) {
    this.jmsMessagingTemplate = jmsMessagingTemplate;
    this.queue = queue;
    this.topic = topic;
  }

  @GetMapping(value = "/queue")
  public String sendQueue(String msg) {
    try {
      jmsMessagingTemplate.convertAndSend(queue, msg);
    } catch (Exception ex) {
      log.error("test queue exception: ", ex);
      return "failure";
    }

    return "success";
  }

  @GetMapping(value = "/topic")
  public String sendTopic(String msg) {
    try {
      jmsMessagingTemplate.convertAndSend(topic, msg);
    } catch (Exception ex) {
      log.error("test topic exception: ", ex);
      return "failure";
    }

    return "success";
  }
}
