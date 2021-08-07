package com.homework.dynamic.datasource.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author bob
 */
@EnableTransactionManagement
@Configuration
@PropertySource(value = "classpath:jdbc.properties", encoding = "UTF-8")
public class JdbcConfiguration implements TransactionManagementConfigurer {

  /**
   * 主库配置
   */
  @Value("${datasource.user}")
  private String userName;
  @Value("${datasource.password}")
  private String password;
  @Value("${datasource.url}")
  private String url;

  /**
   * 从库配置
   */
  @Value("${datasource.slave.user}")
  private String slaveUserName;
  @Value("${datasource.slave.password}")
  private String slavePassword;
  @Value("${datasource.slave.url}")
  private String slaveUrl;

  /**
   * 从库配置
   */
  @Value("${datasource.slave2.user}")
  private String slave2UserName;
  @Value("${datasource.slave2.password}")
  private String slave2Password;
  @Value("${datasource.slave2.url}")
  private String slave2Url;

  @Bean
  public DataSource masterDataSource() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(userName);
    dataSource.setPassword(password);
    dataSource.setURL(url);
    return dataSource;
  }

  @Bean
  public DataSource slaveDataSource() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(slaveUserName);
    dataSource.setPassword(slavePassword);
    dataSource.setURL(slaveUrl);
    return dataSource;
  }

  @Bean
  public DataSource slave2DataSource() {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUser(slave2UserName);
    dataSource.setPassword(slave2Password);
    dataSource.setURL(slave2Url);
    return dataSource;
  }

  /**
   * 定义动态数据源
   *
   * @return 数据源
   */
  @Primary
  @Bean
  public DataSource dataSource() {
    DynamicDatasource dataSource = new DynamicDatasource();
    // 初始化值必须设置进去  且给一个默认值
    dataSource.setTargetDataSources(new HashMap<Object, Object>(3) {{
      put(DynamicDatasourceId.MASTER, masterDataSource());
      put(DynamicDatasourceId.SLAVE1, slaveDataSource());
      put(DynamicDatasourceId.SLAVE2, slave2DataSource());

      // 顺手注册上去，方便后续的判断
      DynamicDatasourceId.DATA_SOURCE_IDS.add(DynamicDatasourceId.MASTER);
      DynamicDatasourceId.DATA_SOURCE_IDS.add(DynamicDatasourceId.SLAVE1);
      DynamicDatasourceId.DATA_SOURCE_IDS.add(DynamicDatasourceId.SLAVE2);
    }});

    dataSource.setDefaultTargetDataSource(masterDataSource());
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager dataSourceTransactionManager
        = new DataSourceTransactionManager(dataSource());
    // 让事务管理器进行只读事务层面上的优化，建议开启
    dataSourceTransactionManager.setEnforceReadOnly(true);
    return dataSourceTransactionManager;
  }

  @Override
  public TransactionManager annotationDrivenTransactionManager() {
    return transactionManager();
  }
}
