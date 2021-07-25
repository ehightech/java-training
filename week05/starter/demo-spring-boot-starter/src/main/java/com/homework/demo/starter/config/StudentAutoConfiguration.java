package com.homework.demo.starter.config;

import com.homework.demo.starter.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

  private final StudentProperties properties;

  @Autowired
  public StudentAutoConfiguration(StudentProperties properties) {
    this.properties = properties;
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = "spring.starter", value = "enabled", havingValue = "true")
  public Student student() {
    return new Student(
        properties.getId(),
        properties.getName(),
        "studentTest",
        null);
  }
}
