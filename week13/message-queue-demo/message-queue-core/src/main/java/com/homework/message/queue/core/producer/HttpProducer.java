package com.homework.message.queue.core.producer;

import com.homework.message.queue.core.broker.Message;
import com.homework.message.queue.core.common.Constant;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * http 消息生产者
 *
 * @author bob
 */
public class HttpProducer implements Producer {

  private final RestTemplate restTemplate = new RestTemplate();

  private Map<String, Object> properties;

  public HttpProducer(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public boolean send(String topic, String message) {
    String url = properties.get(Constant.URL).toString();
    String brokerUrl = url + "/send";
    HttpEntity<Message> request = new HttpEntity<>(new Message(topic, message));
    ResponseEntity<Boolean> response = restTemplate.postForEntity(brokerUrl, request, Boolean.class);
    if (response.getBody() == null) {
      return false;
    }

    return response.getBody();
  }
}
