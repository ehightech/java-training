package com.homework.db.frame.datasource.controller;


import com.homework.db.frame.datasource.entity.User;
import com.homework.db.frame.datasource.service.IUserService;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bob
 * @since 2021-08-08
 */
@RestController
@RequestMapping("/test/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final IUserService userService;

  @GetMapping("/write")
  public String write(){
    int age = new Random(System.currentTimeMillis()).nextInt(100) + 1;
    String name = "person" + age;
    boolean result = userService.save(User.builder().age(age).name(name).build());

    return result ? name : "failure";
  }

  @GetMapping("/read")
  public User read(){
    return userService.getById(1);
  }
}

