package com.meli.SpringbootBuildingblocks;

import com.meli.SpringbootBuildingblocks.util.ScopeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    ScopeUtils.calculateScopeSuffix();
    SpringApplication.run(Application.class, args);
  }
}
