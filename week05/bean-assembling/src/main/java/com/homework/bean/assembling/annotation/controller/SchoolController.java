package com.homework.bean.assembling.annotation.controller;

import com.homework.bean.assembling.annotation.service.SchoolService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller("schoolController")
public class SchoolController {

  @Resource(name = "schoolService")
  private SchoolService schoolService;

  public void select() {
    schoolService.select();
    System.out.println("school controller");
  }
}
