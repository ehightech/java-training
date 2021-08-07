package com.homework.dynamic.datasource.service.impl;

import com.homework.dynamic.datasource.annotation.DynamicDatasourceSwitch;
import com.homework.dynamic.datasource.configuration.DynamicDatasourceId;
import com.homework.dynamic.datasource.service.TestService;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author bob
 */
@Log4j2
@Service
public class TestServiceImpl implements TestService {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public TestServiceImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @DynamicDatasourceSwitch
  @Override
  public void testDatasourceSwitch() {
    String sql = "update demo_user_info set comment = 'master'";
    jdbcTemplate.update(sql);
  }

  @DynamicDatasourceSwitch(datasourceId = DynamicDatasourceId.SLAVE1)
  @Override
  public void testDatasourceSwitch1() {
    String sql = "select * from demo_user_info where comment = 'slave1'";
    List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql);
    log.info("query result1: {}", resultMap);
  }

  @DynamicDatasourceSwitch(datasourceId = DynamicDatasourceId.SLAVE2)
  @Override
  public void testDatasourceSwitch2() {
    String sql = "select * from demo_user_info where comment = 'slave2'";
    List<Map<String, Object>> resultMap = jdbcTemplate.queryForList(sql);
    log.info("query result2: {}", resultMap);
  }
}
