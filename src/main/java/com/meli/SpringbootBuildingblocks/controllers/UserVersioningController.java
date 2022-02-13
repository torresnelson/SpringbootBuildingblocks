package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.dtos.UserDtoV1;
import com.meli.SpringbootBuildingblocks.dtos.UserDtoV2;
import com.meli.SpringbootBuildingblocks.dtos.UserModelMapperDto;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import java.util.Optional;
import javax.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning/uri/users")
public class UserVersioningController {

  private final UserService userService;

  private final ModelMapper modelMapper;

  @Autowired
  public UserVersioningController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping({"/v1.0/{id}", "/v1.1/{id}"})
  public ResponseEntity<UserDtoV1> getUserByIdVersion1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

    Optional<User> userOptional = userService.getUserById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("user not found");
    }

    User user = userOptional.get();
    UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);

    return new ResponseEntity<>(userDtoV1, HttpStatus.OK);
  }

  @GetMapping({"/v2.0/{id}", "/v2.1/{id}"})
  public ResponseEntity<UserDtoV2> getUserByIdVersion2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

    Optional<User> userOptional = userService.getUserById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("user not found");
    }

    User user = userOptional.get();
    UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);

    return new ResponseEntity<>(userDtoV2, HttpStatus.OK);
  }

}
