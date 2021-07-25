package com.homework.demo.starter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  public static String getCurrentDate() {
    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
    Date d= new Date();
    return sdf.format(d);
  }
}
