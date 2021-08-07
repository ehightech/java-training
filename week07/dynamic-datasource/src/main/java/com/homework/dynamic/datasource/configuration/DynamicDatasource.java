package com.homework.dynamic.datasource.configuration;

import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author bob
 */
@Log4j2
public class DynamicDatasource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    String datasourceId = DynamicDatasourceContextHolder.getDatasourceId();
    if (Objects.nonNull(datasourceId)) {
      log.info("获取数据源，线程: {}, 数据源: {}",
          Thread.currentThread().getName(),
          datasourceId);
    }

    return datasourceId;
  }
}
