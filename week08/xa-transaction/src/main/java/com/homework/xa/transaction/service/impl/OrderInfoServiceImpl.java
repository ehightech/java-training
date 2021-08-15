package com.homework.xa.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yitter.idgen.YitIdHelper;
import com.homework.xa.transaction.entity.OrderInfo;
import com.homework.xa.transaction.mapper.OrderInfoMapper;
import com.homework.xa.transaction.service.IOrderInfoService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author bob
 * @since 2021-08-14
 */
@Log4j2
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements
    IOrderInfoService {

  @Transactional(rollbackFor = Exception.class)
  @ShardingTransactionType(TransactionType.XA)
  @Override
  public boolean create(int count) {
    return doCreate(count);
  }

  @Transactional(rollbackFor = Exception.class)
  @ShardingTransactionType(TransactionType.XA)
  @Override
  public boolean createFailed(int count) {
      boolean isSuccess = doCreate(count);
      log.info("create order result: {}", isSuccess);
      double exVal;
      if (isSuccess) {
        exVal = count / 0;
        log.info("show exVal: {}", exVal);
      }
      return isSuccess;
  }

  private boolean doCreate(int count) {
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
