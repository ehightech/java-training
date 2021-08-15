package com.homework.xa.transaction.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author bob
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

  @Bean
  public PlatformTransactionManager txManager(final DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
}
