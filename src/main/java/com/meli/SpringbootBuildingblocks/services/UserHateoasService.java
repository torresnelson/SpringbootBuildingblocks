package com.meli.SpringbootBuildingblocks.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserHateoasService {

  private final UserRepository userRepository;

  @Autowired
  public UserHateoasService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public EntityModel<User> getUserById(Long id) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User Not found in user repository");
    }
    User user = userOptional.get();
    Long userid = user.getUserId();

    EntityModel<User> entityModel = EntityModel.of(user);
    Link link= WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
    entityModel.add(link);
    return entityModel;
  }

//  public List<User> getAllUsers() {
//  }
}
