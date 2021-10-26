package com.meli.SpringbootBuildingblocks.controller;

import com.meli.SpringbootBuildingblocks.dto.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World!!!";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("Nelson", "Torres", "Montevideo");
    }
}
