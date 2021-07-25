package com.homework.starter.test.controller;

import com.homework.demo.starter.service.Klass;
import com.homework.demo.starter.service.School;
import com.homework.demo.starter.service.Student;
import com.homework.demo.starter.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/starter")
public class StarterTestController {

  private final Student student;
  private final Klass klass;
  private final School school;

  @Autowired
  public StarterTestController(Student student, Klass klass,
      School school) {
    this.student = student;
    this.klass = klass;
    this.school = school;
  }

  @ResponseBody
  @RequestMapping("/student")
  public String studentTest() {
    student.init();
    student.print();
    return DateUtil.getCurrentDate();
  }

  @ResponseBody
  @RequestMapping("/klass")
  public String klassTest() {
    klass.dong();
    return DateUtil.getCurrentDate();
  }

  @ResponseBody
  @RequestMapping("/school")
  public String schoolTest() {
    school.ding();
    return DateUtil.getCurrentDate();
  }
}
