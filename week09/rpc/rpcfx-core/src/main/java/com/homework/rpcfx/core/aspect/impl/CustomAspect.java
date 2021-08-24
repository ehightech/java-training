package com.homework.rpcfx.core.aspect.impl;

import com.homework.rpcfx.core.api.Filter;
import com.homework.rpcfx.core.api.RpcfxRequest;
import com.homework.rpcfx.core.aspect.IAspect;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bob
 */
@Slf4j
public class CustomAspect implements IAspect {

  @Override
  public boolean before(RpcfxRequest request, Filter... filters) {
    log.info("execute before aspect");
    if (null != filters) {
      for (Filter filter : filters) {
        if (!filter.filter(request)) {
          return false;
        }
      }
    }

    return true;
  }
}
