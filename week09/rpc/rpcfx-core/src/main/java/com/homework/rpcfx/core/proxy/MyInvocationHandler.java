package com.homework.rpcfx.core.proxy;

import com.alibaba.fastjson.JSON;
import com.homework.rpcfx.core.api.Filter;
import com.homework.rpcfx.core.api.RpcfxRequest;
import com.homework.rpcfx.core.api.RpcfxResponse;
import com.homework.rpcfx.core.aspect.IAspect;
import com.homework.rpcfx.core.client.netty.RpcFxClient;
import com.homework.rpcfx.core.exception.RpcFxException;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author bob
 */
public class MyInvocationHandler implements InvocationHandler {

  public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

  private final IAspect aspect;
  private final Class<?> serviceClass;
  private final String url;
  private final Filter[] filters;

  public <T> MyInvocationHandler(IAspect aspect,
      Class<T> serviceClass, String url, Filter... filters) {
    this.aspect = aspect;
    this.serviceClass = serviceClass;
    this.url = url;
    this.filters = filters;
  }

  // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
  // int byte char float double long bool
  // [], data class

  @Override
  public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

    // 加filter地方之二
    // mock == true, new Student("hubao");

    RpcfxRequest request = new RpcfxRequest();
    request.setServiceClass(this.serviceClass.getName());
    request.setMethod(method.getName());
    request.setParams(params);

    if (!aspect.before(request, filters)) {
      return null;
    }

//    RpcfxResponse response = post(request, url);
    RpcFxClient client = new RpcFxClient(request, url);
    RpcfxResponse response = client.connect("localhost", 8080);

    // 加filter地方之三
    // Student.setTeacher("cuijing");

    // 这里判断response.status，处理异常
    // 考虑封装一个全局的RpcfxException
    if (!response.isStatus()) {
      response.setException(new RpcFxException("RPC exception happened"));
      return JSON.parse(String.valueOf(response.getResult()));
    }

    return JSON.parse(response.getResult().toString());
  }

  private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
    String reqJson = JSON.toJSONString(req);
    System.out.println("req json: " + reqJson);

    // 1.可以复用client
    // 2.尝试使用httpclient或者netty client
    OkHttpClient client = new OkHttpClient();
    final Request request = new Request.Builder()
        .url(url)
        .post(RequestBody.create(JSONTYPE, reqJson))
        .build();
    String respJson = client.newCall(request).execute().body().string();
    System.out.println("resp json: " + respJson);
    return JSON.parseObject(respJson, RpcfxResponse.class);
  }
}
