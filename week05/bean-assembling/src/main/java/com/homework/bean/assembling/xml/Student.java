package com.homework.bean.assembling.xml;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private String name;
  private String grade;
  private List<String> courseList;
}
