package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserAlreadyExistsException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.exceptions.UsernameNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Api(tags = "User management RESTful Services", value = "UserController", description = "Controller for user management service")
@Validated
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(value = "Retrieve list of users")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @ApiOperation(value = "Create new user")
  @PostMapping("")
  public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created,") @Valid @RequestBody User user,
      UriComponentsBuilder builder) {
    try {
      userService.createUser(user);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(builder.path("/{id}").buildAndExpand(user.getUserId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    } catch (UserAlreadyExistsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @ApiOperation(value = "Retrieve user by id")
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") @Min(1) Long id) {
    try {
      return new ResponseEntity<>(userService.getUserById(id).get(), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
    try {
      return userService.updateUserById(id, user);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public void deleteUserById(@PathVariable("id") Long id) {
    userService.deleteUserById(id);
  }

  @GetMapping("/by-username/{username}")
  public User getUserByUsername(@PathVariable("username") String username)
      throws UsernameNotFoundException {
    User user = userService.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username " + username + " Not found in user repository");
    }
    return user;
  }
}
