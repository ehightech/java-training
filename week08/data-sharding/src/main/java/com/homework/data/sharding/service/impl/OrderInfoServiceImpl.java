package com.homework.data.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yitter.idgen.YitIdHelper;
import com.homework.data.sharding.entity.OrderInfo;
import com.homework.data.sharding.mapper.OrderInfoMapper;
import com.homework.data.sharding.service.IOrderInfoService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author bob
 * @since 2021-08-14
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements
    IOrderInfoService {

  @Override
  public boolean create(int count) {
    Random random = new Random();
    List<OrderInfo> OrderInfoList = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      long memberId = random.nextInt(10000);
      OrderInfo orderInfo = OrderInfo.builder()
          .id(YitIdHelper.nextId())
          .memberId(memberId)
          .memberUsername("member" + memberId)
          .orderSn(String.valueOf(YitIdHelper.nextId()))
          .createTime(new Date(System.currentTimeMillis()))
          .build();
      OrderInfoList.add(orderInfo);
    }

    return saveBatch(OrderInfoList);
  }

  @Override
  public boolean update(long id, String name) {
    OrderInfo orderInfo = OrderInfo.builder().id(id).memberUsername(name).build();
    return updateById(orderInfo);
  }
}
