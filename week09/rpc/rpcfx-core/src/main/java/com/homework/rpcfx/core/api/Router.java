package com.homework.rpcfx.core.api;

import java.util.List;

public interface Router {

  List<String> route(List<String> urls);
}
