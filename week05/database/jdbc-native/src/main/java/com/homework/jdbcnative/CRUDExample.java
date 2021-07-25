package com.homework.jdbcnative;

import com.homework.jdbcnative.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CRUDExample {

  public static void main(String[] args) {
    Connection conn = null;
    try {
      // 连接
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false);

      // 新增1
      String sqlCreate = "insert into demo_user_info(name, sex, age, comment) values('张三', 0, 20, '测试')";
      PreparedStatement preparedStatement = conn.prepareStatement(sqlCreate);
      int count = preparedStatement.executeUpdate();
      System.out.println("create record count: " + count);

      // 新增2
      sqlCreate = "insert into demo_user_info(name, sex, age, comment) values('赵六', 1, 19, '生产')";
      preparedStatement = conn.prepareStatement(sqlCreate);
      count = preparedStatement.executeUpdate();
      System.out.println("create record count: " + count);

      // 修改
      String sqlUpdate = "update demo_user_info set comment='生产' where name='张三'";
      preparedStatement = conn.prepareStatement(sqlUpdate);
      count = preparedStatement.executeUpdate();
      System.out.println("update record count: " + count);

      // 查询
      String sqlRead = "select * from demo_user_info";
      preparedStatement = conn.prepareStatement(sqlRead);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        boolean sex = resultSet.getBoolean("sex");
        int age = resultSet.getInt("age");
        String comment = resultSet.getString("comment");
        System.out.println("id=" + id
            +  ";name=" + name
            +  ";sex=" + sex
            +  ";age=" + age
            +  ";comment=" + comment
        );
      }

      // 删除
      String sqlDelete = "delete from demo_user_info where name='张三'";
      preparedStatement = conn.prepareStatement(sqlDelete);
      preparedStatement.executeUpdate();
    } catch (Exception ex) {
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
