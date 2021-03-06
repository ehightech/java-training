package com.homework.db.efficiency.test.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 原生jdbc数据库工具类
 *
 * @author bob
 */
public class DBUtil {

  private static String driver;
  private static String url;
  private static String user;
  private static String password;

  static {
    try {
      //读取配置文件
      InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
      Properties properties = new Properties();
      //加载配置文件
      properties.load(in);
      //获取配置文件中的数据
      driver = properties.getProperty("driver");
      url = properties.getProperty("url");
      user = properties.getProperty("user");
      password = properties.getProperty("password");
      //加载数据库链接驱动
      Class.forName(driver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取一个数据库链接
   */
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }
}
