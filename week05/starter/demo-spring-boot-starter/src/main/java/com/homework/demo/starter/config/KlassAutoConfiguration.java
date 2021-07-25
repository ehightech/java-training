package com.homework.demo.starter.config;

import com.homework.demo.starter.service.Klass;
import com.homework.demo.starter.service.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Klass.class)
public class KlassAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(Klass.class)
  public Klass klass() {
    List<Student> studentList = new ArrayList<>(3);
    for (int i = 0; i < 3; i++) {
      Student student = new Student(1, "student" + i, null, null);
      studentList.add(student);
    }

    return new Klass(studentList);
  }
}
