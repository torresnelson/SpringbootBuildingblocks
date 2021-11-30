package com.meli.SpringbootBuildingblocks.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

  private final UserService userService;

  @Autowired
  public UserMappingJacksonController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<MappingJacksonValue> getUserById(@PathVariable("id") @Min(1) Long id) {
    try {
      Optional<User> userOptional = userService.getUserById(id);
      User user = userOptional.get();

      Set<String> fields = new HashSet<String>();
      fields.add("user_id");
      fields.add("username");
      fields.add("orders");

      FilterProvider filterProvider = new SimpleFilterProvider()
          .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

      MappingJacksonValue mapper = new MappingJacksonValue(user);

      mapper.setFilters(filterProvider);

      return new ResponseEntity<>(mapper, HttpStatus.OK);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  //Fields getting by request param.
  @GetMapping("/params/{id}")
  public ResponseEntity<MappingJacksonValue> getUserById(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {

    try {
      Optional<User> userOptional = userService.getUserById(id);
      User user = userOptional.get();

      FilterProvider filterProvider = new SimpleFilterProvider()
          .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

      MappingJacksonValue mapper = new MappingJacksonValue(user);

      mapper.setFilters(filterProvider);

      return new ResponseEntity<>(mapper, HttpStatus.OK);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
