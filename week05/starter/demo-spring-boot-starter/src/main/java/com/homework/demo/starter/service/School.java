package com.homework.demo.starter.service;

import com.homework.demo.starter.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class School implements ISchool {

  Klass klass;

  Student student;

  @Override
  public void ding() {
    System.out.println(DateUtil.getCurrentDate() + ": Class1 have "
        + this.klass.getStudents().size()
        + " students and one is "
        + this.student);
  }
}
