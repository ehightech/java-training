package com.homework.xa.transaction.controller;

import com.homework.xa.transaction.entity.OrderInfo;
import com.homework.xa.transaction.service.IOrderInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author bob
 * @since 2021-08-14
 */
@RestController
@RequestMapping("/order-info")
public class OrderInfoController {

  private final IOrderInfoService orderInfoService;

  @Autowired
  public OrderInfoController(IOrderInfoService orderInfoService) {
    this.orderInfoService = orderInfoService;
  }

  @GetMapping
  public List<OrderInfo> getAll() {
    return orderInfoService.list();
  }

  @GetMapping("/{id}")
  public OrderInfo getOne(@PathVariable("id") long id) {
    return orderInfoService.getById(id);
  }

  @PostMapping
  public boolean create(int count) {
    return orderInfoService.create(count);
  }

  @PostMapping("/failed")
  public boolean createFailed(int count) {
    return orderInfoService.createFailed(count);
  }

  @PutMapping
  public boolean update(long id, String name) {
    return orderInfoService.update(id, name);
  }

  @DeleteMapping("/{id}")
  public boolean delete(@PathVariable("id") long id) {
    return orderInfoService.removeById(id);
  }

}

