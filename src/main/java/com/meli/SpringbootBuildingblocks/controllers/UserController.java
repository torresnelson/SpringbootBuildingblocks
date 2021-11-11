package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserAlreadyExistsException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/udemy-course/api")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping("/users")
  public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
    try {
      userService.createUser(user);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    } catch (UserAlreadyExistsException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @GetMapping("/users/{id}")
  public Optional<User> getUserById(@PathVariable("id") Long id) {
    try {
      return userService.getUserById(id);
    } catch (UserNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
  }

  @PutMapping("/users/{id}")
  public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
    try {
      return userService.updateUserById(id, user);
    } catch (UserNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
  }

  @DeleteMapping("/users/{id}")
  public void deleteUserById(@PathVariable("id") Long id) {
    userService.deleteUserById(id);
  }

  @GetMapping("/users/byusername/{username}")
  public User getUserByUsername(@PathVariable("username") String username) {
    return userService.getUserByUsername(username);
  }
}
