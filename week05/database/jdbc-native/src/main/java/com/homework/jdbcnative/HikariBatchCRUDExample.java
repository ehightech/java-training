package com.homework.jdbcnative;

import com.homework.jdbcnative.util.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Objects;

/**
 * 批量操作
 */
public class HikariBatchCRUDExample {

  public static void main(String[] args) {
    Connection conn = null;
    try {
      // 连接
      conn = DBManager.getConnection();
      conn.setAutoCommit(false);

      // 新增
      Statement statement = conn.createStatement();
      String sqlCreate = "insert into demo_user_info(name, sex, age, comment) values('测试1', 0, 20, '测试1')";
      statement.addBatch(sqlCreate);
      sqlCreate = "insert into demo_user_info(name, sex, age, comment) values('测试2', 0, 20, '测试2')";
      statement.addBatch(sqlCreate);
      sqlCreate = "insert into demo_user_info(name, sex, age, comment) values('测试3', 0, 20, '测试3')";
      statement.addBatch(sqlCreate);
      int result[] = statement.executeBatch();
      System.out.println(Arrays.toString(result));
      System.out.println("batch create record count: " + Arrays.toString(result));

      // 修改
      statement = conn.createStatement();
      String sqlUpdate = "update demo_user_info set comment='测试一' where name='测试1'";
      statement.addBatch(sqlUpdate);
      sqlUpdate = "update demo_user_info set comment='测试二' where name='测试2'";
      statement.addBatch(sqlUpdate);
      sqlUpdate = "update demo_user_info set comment='测试三' where name='测试3'";
      statement.addBatch(sqlUpdate);
      result = statement.executeBatch();
      System.out.println("update record count: " + Arrays.toString(result));

      // 查询
      String sqlRead = "select * from demo_user_info";
      PreparedStatement preparedStatement = conn.prepareStatement(sqlRead);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        boolean sex = resultSet.getBoolean("sex");
        int age = resultSet.getInt("age");
        String comment = resultSet.getString("comment");
        System.out.println("id=" + id
            + ";name=" + name
            + ";sex=" + sex
            + ";age=" + age
            + ";comment=" + comment
        );
      }

      // 删除
      String sqlDelete = "delete from demo_user_info where name='测试3'";
      preparedStatement = conn.prepareStatement(sqlDelete);
      preparedStatement.executeUpdate();
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
          conn.commit();
          conn.close();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      }
    }
  }
}
