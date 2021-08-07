package com.homework.dynamic.datasource.configuration;

/**
 * @author bob
 */
public abstract class DynamicDatasourceContextHolder {

  private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

  public static void setDatasourceId(final String datasourceId) {
    CONTEXT_HOLDER.set(datasourceId);
  }

  public static String getDatasourceId() {
    return CONTEXT_HOLDER.get();
  }

  public static void clearDatasourceId() {
    CONTEXT_HOLDER.remove();
  }
}
