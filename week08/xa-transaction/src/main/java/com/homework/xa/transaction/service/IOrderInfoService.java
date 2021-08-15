package com.homework.xa.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.homework.xa.transaction.entity.OrderInfo;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author bob
 * @since 2021-08-14
 */
public interface IOrderInfoService extends IService<OrderInfo> {

  /**
   * 创建订单
   *
   * @param count 创建数量
   * @return true: 成功, false: 失败
   */
  boolean create(int count);

  /**
   * 创建订单失败
   *
   * @param count 创建数量
   * @return true: 成功, false: 失败
   */
  boolean createFailed(int count);

  /**
   * 修改订单
   *
   * @param id   订单id
   * @param name 新会员名称
   * @return true: 成功, false: 失败
   */
  boolean update(long id, String name);
}
