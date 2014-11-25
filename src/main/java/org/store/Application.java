package org.store;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {
  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping(value = "/")
  public String index() {
    return "public/index";
  }

  @PreAuthorize("hasRole('admin')")
  @RequestMapping(value = "/home")
  public String home() {
    return "private/home";
  }

  @RequestMapping(value = "/signin")
  public String signin() {
    return "acesso/signin";
  }

  @RequestMapping(value = "/signup")
  public String signup() {
    return "acesso/signup";
  }
}
