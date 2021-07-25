package com.homework.demo.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.starter")
public class StudentProperties {

  private int id;
  private String name;
}
