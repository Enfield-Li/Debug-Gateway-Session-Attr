package com.example.customer;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
class CustomerController {

  @GetMapping
  void test(HttpSession session) {
    System.out.println("***** New request *****");
    System.out.println("Session id: " + session.getId());

    System.out.println("userId: " + session.getAttribute("userId"));
  }
}
