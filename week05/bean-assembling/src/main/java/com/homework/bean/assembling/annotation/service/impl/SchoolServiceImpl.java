package com.homework.bean.assembling.annotation.service.impl;

import com.homework.bean.assembling.annotation.dao.SchoolDao;
import com.homework.bean.assembling.annotation.service.SchoolService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {

  @Resource(name = "schoolDao")
  private SchoolDao schoolDao;

  @Override
  public void select() {
    schoolDao.select();
    System.out.println("school service");
  }
}
