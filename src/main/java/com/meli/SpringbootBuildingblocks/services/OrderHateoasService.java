package com.meli.SpringbootBuildingblocks.services;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

@Service
public class OrderHateoasService {

  private final UserRepository userRepository;

  @Autowired
  public OrderHateoasService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public CollectionModel<Order> getAllOrders(Long userid) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(userid);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("User not found");
    }
    List<Order> allOrders = userOptional.get().getOrders();
    return CollectionModel.of(allOrders);
  }
}
