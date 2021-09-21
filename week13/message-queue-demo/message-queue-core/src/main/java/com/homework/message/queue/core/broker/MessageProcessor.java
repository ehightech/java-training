package com.homework.message.queue.core.broker;

import com.homework.message.queue.core.broker.queue.CustomQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 消息处理器
 *
 * @author bob
 */
@Component
@Log4j2
public class MessageProcessor {

  private Map<String, CustomQueue> queueMap = new HashMap<>();

  public boolean send(String topic, String content) {
    CustomQueue queue = queueMap.getOrDefault(topic, new CustomQueue());
    queue.put(content);
    queueMap.put(topic, queue);
    return true;
  }

  public List<String> poll(String topic, String group, int rate) {
    CustomQueue queue = queueMap.get(topic);

    List<String> messages = new ArrayList<>();
    if (queue == null) {
      return messages;
    }

    log.info("queue message amount : " + queue.size());
    while (!queue.isEmpty() || rate > 0) {
      String message = queue.get(group);
      if (message == null) {
        break;
      }
      messages.add(message);
      rate -= 1;
    }

    return messages;
  }
}
