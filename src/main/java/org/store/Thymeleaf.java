package org.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;

@Configuration
public class Thymeleaf {
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine  =  new SpringTemplateEngine();
    engine.setDialect( new SpringSecurityDialect() );
    return engine;
  }
}
