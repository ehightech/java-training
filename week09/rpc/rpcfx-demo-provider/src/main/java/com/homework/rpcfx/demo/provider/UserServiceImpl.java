package com.homework.rpcfx.demo.provider;


import com.homework.rpcfx.demo.api.User;
import com.homework.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService {

  @Override
  public User findById(int id) {
    return new User(id, "KK" + System.currentTimeMillis());
  }
}
