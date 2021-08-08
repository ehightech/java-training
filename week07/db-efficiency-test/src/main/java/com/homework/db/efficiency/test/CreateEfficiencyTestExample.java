package com.homework.db.efficiency.test;

import com.homework.db.efficiency.test.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StopWatch;

/**
 * @author bob
 */
@Log4j2
public class CreateEfficiencyTestExample {

  public static void main(String[] args) {
    Connection conn = null;
    try {
      // 连接
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false);

      // 新增
      int createNum = 100_0000;
      String sql;
      String personName;
      StopWatch sw = new StopWatch();
      sw.start();
      for (int i = 0; i < createNum; i++) {
        personName = "person" + i;
        sql = "insert into demo_user_info(name, sex, age, comment) values('%s', 0, 20, '%s')";
        sql = String.format(sql, personName, personName);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();        ;
      }
      conn.commit();
      sw.stop();
      log.info("数据库插入耗时: {}", sw.getTotalTimeSeconds());
    } catch (Exception ex) {
      try {
        conn.rollback();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
      System.out.println(ex);
    } finally {
      if (Objects.nonNull(conn)) {
        try {
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
  }
}
