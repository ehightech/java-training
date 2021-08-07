package com.homework.dynamic.datasource.annotation;

import com.homework.dynamic.datasource.configuration.DynamicDatasourceId;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bob
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDatasourceSwitch {

  String datasourceId() default DynamicDatasourceId.MASTER;
}
