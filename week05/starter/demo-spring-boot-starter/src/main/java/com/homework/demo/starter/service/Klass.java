package com.homework.demo.starter.service;

import com.homework.demo.starter.util.DateUtil;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Klass {
    
    List<Student> students;
    
    public void dong(){
        String dateStr = DateUtil.getCurrentDate();
        System.out.println(dateStr + ": " + this.getStudents());
    }
}
