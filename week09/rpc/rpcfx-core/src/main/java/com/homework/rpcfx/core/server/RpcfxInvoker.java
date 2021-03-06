package com.homework.rpcfx.core.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.homework.rpcfx.core.api.RpcfxRequest;
import com.homework.rpcfx.core.api.RpcfxResolver;
import com.homework.rpcfx.core.api.RpcfxResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcfxInvoker {

  private RpcfxResolver resolver;

  public RpcfxInvoker(RpcfxResolver resolver) {
    this.resolver = resolver;
  }

  public RpcfxResponse invoke(RpcfxRequest request) {
    RpcfxResponse response = new RpcfxResponse();
    String serviceClass = request.getServiceClass();

    // 作业1：改成泛型和反射
    Object service = resolver.resolve(serviceClass);//this.applicationContext.getBean(serviceClass);

    try {
      Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
      Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
      // 两次json序列化能否合并成一个
      response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
//      int ex = 1 / 0;
      response.setStatus(true);
      return response;
    } catch (IllegalAccessException | InvocationTargetException e) {
      // 3.Xstream

      // 2.封装一个统一的RpcfxException
      // 客户端也需要判断异常
      e.printStackTrace();
      response.setException(e);
      response.setStatus(false);
      return response;
    }
  }

  private Method resolveMethodFromClass(Class<?> klass, String methodName) {
    return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst()
        .get();
  }

}
