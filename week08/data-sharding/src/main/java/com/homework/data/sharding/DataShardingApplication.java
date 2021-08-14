package com.homework.data.sharding;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.flywaydb.core.Flyway;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bob
 */
@MapperScan(basePackages = "com.homework.data.sharding.mapper")
@SpringBootApplication
public class DataShardingApplication {

  private final DataSource dataSource;

  @Autowired
  public DataShardingApplication(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public static void main(String[] args) {
    SpringApplication.run(DataShardingApplication.class, args);
  }

  @PostConstruct
  public void dbMigration() {
    ShardingDataSource shardingDataSource = (ShardingDataSource) dataSource;
    shardingDataSource.getDataSourceMap().values().forEach(
        ds -> Flyway.configure().dataSource(ds).outOfOrder(true).baselineOnMigrate(true).load()
            .migrate());
  }

}
