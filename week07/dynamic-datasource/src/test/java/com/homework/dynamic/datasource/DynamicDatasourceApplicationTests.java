package com.homework.dynamic.datasource;

import com.homework.dynamic.datasource.configuration.DynamicDatasourceContextHolder;
import com.homework.dynamic.datasource.configuration.DynamicDatasourceId;
import com.homework.dynamic.datasource.configuration.JdbcConfiguration;
import com.homework.dynamic.datasource.service.TestService;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {JdbcConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan(basePackages = {"com.homework.dynamic.datasource"})
class DynamicDatasourceApplicationTests {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private DataSource dataSource;
  @Resource
  private TestService testService;

  @Test
  void contextLoads() {
  }

  @Test
  public void datasource_test() {
    System.out.println(jdbcTemplate.getDataSource() == dataSource);
    System.out.println(DataSourceUtils.getConnection(jdbcTemplate.getDataSource()));

    // 切换数据源
    DynamicDatasourceContextHolder.setDatasourceId(DynamicDatasourceId.SLAVE1);
    System.out.println(jdbcTemplate.getDataSource() == dataSource);
    System.out.println(DataSourceUtils.getConnection(jdbcTemplate.getDataSource()));

    // 切换数据源
    DynamicDatasourceContextHolder.setDatasourceId(DynamicDatasourceId.SLAVE2);
    System.out.println(jdbcTemplate.getDataSource() == dataSource);
    System.out.println(DataSourceUtils.getConnection(jdbcTemplate.getDataSource()));

    // 完成操作后, 最好把数据源再set回去, 否则可能会对该线程后续再使用JdbcTemplate的时候造成影响
    DynamicDatasourceContextHolder.setDatasourceId(DynamicDatasourceId.MASTER);
  }

  @Test
  public void datasource2_test() {
    testService.testDatasourceSwitch();
    testService.testDatasourceSwitch1();
    testService.testDatasourceSwitch2();
  }
}
