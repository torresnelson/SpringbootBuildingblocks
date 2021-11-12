package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserAlreadyExistsException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.OrderRepository;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class OrderController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  @GetMapping("/{userid}/orders")
  public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
    Optional<User> user = userRepository.findById(userid);
    if (!user.isPresent()) {
      throw new UserNotFoundException("User not found");
    }
    return user.get().getOrders();
  }

  @PostMapping("/{userid}/orders")
  public ResponseEntity<Order> createOrder(@Valid @PathVariable Long userid,
      @RequestBody Order order)
      throws UserNotFoundException {
    List<Order> orders = new ArrayList<>();
    orders.add(order);
    Optional<User> optionalUser = userRepository.findById(userid);
    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException("User not found.");
    }
    User user = optionalUser.get();
    order.setUser(user);
    orderRepository.saveAndFlush(order);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }
}
