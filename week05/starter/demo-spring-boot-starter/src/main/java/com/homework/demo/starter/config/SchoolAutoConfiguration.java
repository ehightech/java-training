package com.homework.demo.starter.config;

import com.homework.demo.starter.service.Klass;
import com.homework.demo.starter.service.School;
import com.homework.demo.starter.service.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(School.class)
public class SchoolAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(Student.class)
  public Student student() {
    return new Student(
        123,
        "123",
        "student123",
        null);
  }

  @Bean
  @ConditionalOnMissingBean(Klass.class)
  public Klass klass() {
    return new Klass(null);
  }

  @Bean
  @ConditionalOnMissingBean(School.class)
  public School school() {
    return new School(klass(), student());
  }
}
