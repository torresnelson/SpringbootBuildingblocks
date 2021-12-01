package com.meli.SpringbootBuildingblocks.mapper;

import com.meli.SpringbootBuildingblocks.dtos.UserMapStructDTO;
import com.meli.SpringbootBuildingblocks.entities.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  //User to UserMapStructDTO
  UserMapStructDTO userToUserMapStructDTO(User user);


  //List<User> to List<UserMapStructDTO>
  List<UserMapStructDTO> userstoUserMapStructDTOs(List<User> users);

}
