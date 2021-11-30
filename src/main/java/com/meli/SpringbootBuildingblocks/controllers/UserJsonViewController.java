package com.meli.SpringbootBuildingblocks.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.entities.Views;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import java.util.Optional;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/jsonview/users")
@Validated
public class UserJsonViewController {

  private final UserService userService;

  @Autowired
  public UserJsonViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/external/{id}")
  @JsonView(Views.External.class)
  public Optional<User> getUserByIdExternal(@PathVariable("id") @Min(1) Long id) {
    try {
      return userService.getUserById(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
  @GetMapping("/internal/{id}")
  @JsonView(Views.Internal.class)
  public Optional<User> getUserByIdInternal(@PathVariable("id") @Min(1) Long id) {
    try {
      return userService.getUserById(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

}
