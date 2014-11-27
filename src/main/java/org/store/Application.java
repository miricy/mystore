package org.store;

import org.springframework.ui.Model;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import org.store.model.usuario.Usuario;

@Controller
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {
  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping(value = "/")
  public String index(Model model) throws Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("menu", Menu.index());
    model.addAttribute("usuario", auth.getPrincipal());
    return "public/index";
  }

  @PreAuthorize("hasRole('admin')")
  @RequestMapping(value = "/home")
  public String home(Model model) throws Exception {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("menu", Menu.admin());
    model.addAttribute("settings", Menu.settings());
    model.addAttribute("usuario", auth.getPrincipal());
    return "private/home";
  }

  @RequestMapping(value = "/signin")
  public String signin() {
    return "acesso/signin";
  }

  @RequestMapping(value = "/signup")
  public String signup1() {
    return "acesso/signup";
  }

  @RequestMapping(value = "/send_data", method=RequestMethod.POST)
  public String signup2(@ModelAttribute("object") Usuario object, BindingResult result) {
    return "acesso/signin";
  }

  @RequestMapping(value = "/settings")
  public String settings(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("usuario", auth.getPrincipal());
    return "private/settings";
  }

  @RequestMapping(value = "/profile")
  public String profile(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("usuario", auth.getPrincipal());
    return "private/profile";
  }
}
