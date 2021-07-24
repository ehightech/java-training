package com.homework.bean.assembling.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml方式装配bean
 */
public class BeanAssemblingXml {

  public static void main(String[] args) {
    String xmlPath = "bean-assembling-xml.xml";
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
    System.out.println(applicationContext.getBean("cStudent"));
    System.out.println(applicationContext.getBean("sStudent"));
  }
}
