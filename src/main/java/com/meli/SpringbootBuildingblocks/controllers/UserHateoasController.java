package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserHateoasService;
import com.meli.SpringbootBuildingblocks.services.UserService;
import java.util.List;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/hateoas/users")
@Validated
public class UserHateoasController {

  private UserHateoasService userHateoasService;

  @Autowired
  public UserHateoasController(UserHateoasService userHateoasService) {
    this.userHateoasService = userHateoasService;
  }

  @GetMapping("/{id}")
  public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
    try {
      return userHateoasService.getUserById(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

//  @GetMapping("")
//  public List<User> getAllUsers() {
//    return userHateoasService.getAllUsers();
//  }
}
