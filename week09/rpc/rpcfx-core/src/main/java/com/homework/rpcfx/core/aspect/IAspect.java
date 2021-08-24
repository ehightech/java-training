package com.homework.rpcfx.core.aspect;

import com.homework.rpcfx.core.api.Filter;
import com.homework.rpcfx.core.api.RpcfxRequest;

/**
 * @author bob
 */
public interface IAspect {

  /**
   * 前置增强
   *
   * @param request 请求对象
   * @param filters 过滤器
   * @return true: 过滤成功, false: 过滤失败
   */
  boolean before(RpcfxRequest request, Filter... filters);
}
