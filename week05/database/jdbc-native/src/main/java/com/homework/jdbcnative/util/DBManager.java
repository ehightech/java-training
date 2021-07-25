package com.homework.jdbcnative.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

  private static HikariDataSource sHikariDataSource;

  static {
    // 获得属性文件的输入流
    try (InputStream is = DBManager.class.getClassLoader()
        .getResourceAsStream("hikari.properties")) {
      // 加载属性文件并解析：
      Properties props = new Properties();
      props.load(is);
      HikariConfig config = new HikariConfig(props);
      sHikariDataSource = new HikariDataSource(config);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取一个数据库链接
   */
  public static Connection getConnection() throws SQLException {
    return sHikariDataSource.getConnection();
  }
}
