package com.homework.message.queue.example;

import com.homework.message.queue.core.common.Constant;
import com.homework.message.queue.core.consumer.Consumer;
import com.homework.message.queue.core.consumer.HttpConsumer;
import com.homework.message.queue.core.producer.HttpProducer;
import com.homework.message.queue.core.producer.Producer;
import com.homework.message.queue.core.producer.WebsocketProducer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bob
 */
@SpringBootApplication
public class MessageQueueExampleApplication {

  public static void main(String[] args) throws InterruptedException {
    int messageAmount = 10;
    String topic = "testTopic";
    int getRate = 10;

//    startHttpMQProducer(messageAmount, topic);
    startWebsocketMqProducer(messageAmount, topic);

    startHttpMQConsumer(messageAmount, topic, getRate);
  }

  private static void startWebsocketMqProducer(int messageAmount, String topic) {
    Map<String, Object> properties = new HashMap<>(1);
    properties.put(Constant.URL, "ws://localhost:8013/producer");
    properties.put(Constant.SEND_TIMEOUT, 1000);
    Producer producer = new WebsocketProducer(properties);

    int amount = messageAmount;

    System.out.println("start producer test");
    long start = System.currentTimeMillis();

    while (amount > 0) {
      if (producer.send(topic, "producerMessage")) {
        amount -= 1;
      } else {
        System.out.println("send failed");
      }
    }

    System.out.println("Producer " + messageAmount + " messages spend time : " +
        (System.currentTimeMillis() - start) + " ms ");
  }

  private static void startHttpMQConsumer(int messageAmount, String topic, int getRate) {
    Map<String, Object> properties = new HashMap<>(1);
    properties.put("url", "http://localhost:8013");
    properties.put("topic", topic);
    properties.put("group", "groupTest");
    Consumer consumer = new HttpConsumer(properties);
    int amount = messageAmount;

    System.out.println("Start consumer test");
    long start = System.currentTimeMillis();

    while (amount > 0) {
      amount -= consumer.poll(getRate).size();
    }

    System.out.println(
        "Consumer " + messageAmount + " messages spend time : " + (System.currentTimeMillis()
            - start) + " " +
            "ms");
  }

  private static void startHttpMQProducer(int messageAmount, String topic) {
    Map<String, Object> properties = new HashMap<>(1);
    properties.put("url", "http://localhost:8013");
    Producer producer = new HttpProducer(properties);

    System.out.println("start producer test");
    long start = System.currentTimeMillis();

    for (int i = 0; i < messageAmount; i++) {
      if (!producer.send(topic, String.valueOf(i))) {
        System.out.println("send failed");
      }
    }

    System.out.println("Producer " + messageAmount + " messages spend time : " +
        (System.currentTimeMillis() - start) + " ms ");
  }

}
