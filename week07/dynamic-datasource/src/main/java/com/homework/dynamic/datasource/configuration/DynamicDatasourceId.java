package com.homework.dynamic.datasource.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author bob
 */
public class DynamicDatasourceId {

  public static final String MASTER = "master";
  public static final String SLAVE1 = "slave1";
  public static final String SLAVE2 = "slave2";

  /**
   * 保存所有的DATA_SOURCE_IDS
   */
  public static final List<String> DATA_SOURCE_IDS = new ArrayList();

  public static boolean containsDataSourceId(final String dataSourceId) {
    return Objects.nonNull(dataSourceId)
        && !dataSourceId.trim().isEmpty()
        && DATA_SOURCE_IDS.contains(dataSourceId);
  }
}
