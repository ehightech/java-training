package com.homework.bean.assembling.annotation;

import com.homework.bean.assembling.annotation.controller.SchoolController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动装配bean
 */
public class BeanAssemblingAutomation {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "bean-assembling-automation.xml");
    SchoolController schoolController = (SchoolController) applicationContext
        .getBean("schoolController");
    schoolController.select();
  }
}
