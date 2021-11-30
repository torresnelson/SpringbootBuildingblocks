package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.dtos.UserModelMapperDTO;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.UserService;
import java.util.Optional;
import javax.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

  private final UserService userService;

  private final ModelMapper modelMapper;

  @Autowired
  public UserModelMapperController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserModelMapperDTO> getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {

    Optional<User> userOptional = userService.getUserById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("user not found");
    }

    User user = userOptional.get();
    UserModelMapperDTO userModelMapperDTO = modelMapper.map(user, UserModelMapperDTO.class);

    return new ResponseEntity<>(userModelMapperDTO, HttpStatus.OK);
  }
}
