package com.homework.bean.assembling.annotation;

import com.homework.bean.assembling.annotation.controller.SchoolController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注解方式装配bean
 */
public class BeanAssemblingAnnotation {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
        "bean-assembling-annotation.xml");
    SchoolController schoolController = (SchoolController) applicationContext
        .getBean("schoolController");
    schoolController.select();
  }
}
