package com.homework.message.queue.core.consumer;

import com.homework.message.queue.core.common.Constant;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * http 消息消费者
 *
 * @author bob
 */
public class HttpConsumer implements Consumer {

  private final RestTemplate restTemplate = new RestTemplate();

  private Map<String, Object> properties;

  public HttpConsumer(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public List poll(int rate) {
    String topic = properties.get(Constant.TOPIC).toString();
    String group = properties.get(Constant.GROUP).toString();
    String url = properties.get(Constant.URL).toString();
    String brokerUrl = url + "/poll?topic=" + topic + "&rate=" + rate + "&group=" + group;
    ResponseEntity<List> response = restTemplate.getForEntity(brokerUrl, List.class);
    return response.getBody();
  }
}
