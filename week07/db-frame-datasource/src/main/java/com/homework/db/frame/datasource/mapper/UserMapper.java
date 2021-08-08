package com.homework.db.frame.datasource.mapper;

import com.homework.db.frame.datasource.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bob
 * @since 2021-08-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
