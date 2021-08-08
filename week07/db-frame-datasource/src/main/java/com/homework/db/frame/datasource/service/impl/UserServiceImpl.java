package com.homework.db.frame.datasource.service.impl;

import com.homework.db.frame.datasource.service.IUserService;
import com.homework.db.frame.datasource.entity.User;
import com.homework.db.frame.datasource.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bob
 * @since 2021-08-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
