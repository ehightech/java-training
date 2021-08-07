package com.homework.dynamic.datasource.aspect;

import com.homework.dynamic.datasource.annotation.DynamicDatasourceSwitch;
import com.homework.dynamic.datasource.configuration.DynamicDatasourceContextHolder;
import java.lang.reflect.Method;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author bob
 */
@Log4j2
@Order(1)
@Aspect
@Component
public class DynamicDatasourceHandlerAspect {

  @Pointcut("@annotation(com.homework.dynamic.datasource.annotation.DynamicDatasourceSwitch)")
  public void pointcut() {
  }

  @Before("pointcut()")
  public void doBefore(JoinPoint joinPoint) {
    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    DynamicDatasourceSwitch annotationClass = method.getAnnotation(DynamicDatasourceSwitch.class);
    if (Objects.isNull(annotationClass)) {
      annotationClass = joinPoint.getTarget().getClass()
          .getAnnotation(DynamicDatasourceSwitch.class);
      if (Objects.isNull(annotationClass)) {
        log.warn("fail to get annotation DynamicDatasourceSwitch");
        return;
      }
    }

    // 获取注解数据源信息
    String datasourceId = annotationClass.datasourceId();
    // 切换数据源
    DynamicDatasourceContextHolder.setDatasourceId(datasourceId);
    log.info("AOP动态切换数据源，className: {}, methodName: {}, datasourceId: {}",
        joinPoint.getTarget().getClass().getName(),
        method.getName(),
        Objects.nonNull(datasourceId) ? datasourceId : "默认数据源");
  }

  @After("pointcut()")
  public void doAfter(JoinPoint joinPoint) {
    DynamicDatasourceContextHolder.clearDatasourceId();
    log.info("datasource has been cleared");
  }
}
