package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.dtos.UserMapStructDTO;
import com.meli.SpringbootBuildingblocks.mapper.UserMapper;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Autowired
  public UserMapStructController(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @GetMapping
  public List<UserMapStructDTO> getAllUserDtos() {
    return userMapper.userstoUserMapStructDTOs(userRepository.findAll());
  }

}
