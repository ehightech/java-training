package com.homework.spring.kafka.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@ConfigurationProperties(prefix = "mq-info.kafka")
@Component
@Data
public class KafkaProperty {

  private String bootstrapServers;

  private Producer producer;

  private Consumer consumer;
}
