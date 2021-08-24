package com.homework.rpcfx.core.api;

public interface Filter {

  boolean filter(RpcfxRequest request);

  // Filter next();
}
