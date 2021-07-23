package com.homework.bean.assembling.annotation.dao.impl;

import com.homework.bean.assembling.annotation.dao.SchoolDao;
import org.springframework.stereotype.Repository;

@Repository("schoolDao")
public class SchoolDaoImpl implements SchoolDao {

  @Override
  public void select() {
    System.out.println("school dao");
  }
}
