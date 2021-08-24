package com.homework.transaction.demo;

import com.homework.transaction.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:spring-dubbo.xml"})
public class TransactionDemoApplication implements ApplicationRunner {

  @Autowired
  private TransactionService transactionService;

  public static void main(String[] args) {
    SpringApplication.run(TransactionDemoApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    transactionService.transaction();
  }

}
