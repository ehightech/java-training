package com.homework.demo.starter.service;

import com.homework.demo.starter.util.DateUtil;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {

  private int id;
  private String name;
  private String beanName;
  private ApplicationContext applicationContext;

  public void init() {
    String dateStr = DateUtil.getCurrentDate();
    System.out.println(dateStr + ": " + "hello...........");
  }

  public static Student create() {
    return new Student(102, "KK102", null, null);
  }

  public void print() {
    String dateStr = DateUtil.getCurrentDate();
    System.out.println(dateStr + ": " + this.beanName);
    System.out.println(dateStr + ": " + "   context.getBeanDefinitionNames() ===>> "
        + String.join(",", applicationContext.getBeanDefinitionNames()));
  }


}
