package com.homework.rpcfx.core.client;

import com.homework.rpcfx.core.api.Filter;
import com.homework.rpcfx.core.aspect.IAspect;
import com.homework.rpcfx.core.proxy.MyInvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author bob
 */
public class JdkDynamicProxyGenerator {

  public static Object generateJdkProxy(IAspect aspect, Class<?> serviceClass,
      String url, Filter... filters) {
    return Proxy.newProxyInstance(
        serviceClass.getClassLoader(),
        new Class[]{serviceClass},
        new MyInvocationHandler(aspect, serviceClass, url, filters));
  }
}
