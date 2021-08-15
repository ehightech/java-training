package com.homework.xa.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bob
 */
@MapperScan(basePackages = "com.homework.xa.transaction.mapper")
@SpringBootApplication
public class XaTransactionApplication {

  public static void main(String[] args) {
    SpringApplication.run(XaTransactionApplication.class, args);
  }

}
