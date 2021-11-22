package com.meli.SpringbootBuildingblocks.services;

import com.meli.SpringbootBuildingblocks.controllers.OrderHateoasController;
import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserHateoasService {

  private final UserRepository userRepository;

  private final UserService userService;

  @Autowired
  public UserHateoasService(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public EntityModel<User> getUserById(Long id) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User Not found in user repository");
    }
    User user = userOptional.get();
    Long userid = user.getUserId();

    EntityModel<User> entityModel = EntityModel.of(user);
    Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
    entityModel.add(link);
    return entityModel;
  }

  public CollectionModel<User> getAllUsers() throws UserNotFoundException {

    List<User> allUsers = userService.getAllUsers();
    for (User user : allUsers) {
      //Self link
      Long userid = user.getUserId();
      Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
      user.add(link);

      //Relationship link with getAllOrders
      CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userid);
      Link orderLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
      user.add(orderLink);

    }
    return CollectionModel.of(allUsers);

  }

}
