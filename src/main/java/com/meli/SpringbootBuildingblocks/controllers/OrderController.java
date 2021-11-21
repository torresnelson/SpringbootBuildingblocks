package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.OrderNotFoundException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.OrderRepository;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import com.meli.SpringbootBuildingblocks.services.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping("/{userid}/orders")
  public List<Order> getAllOrders(@PathVariable Long userid) {
    try {
      return orderService.getAllUsers(userid);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/{userid}/orders")
  public ResponseEntity<Order> createOrder(@Valid @PathVariable Long userid,
      @RequestBody Order order) {
    try {
      orderService.createOrder(userid, order);
      return new ResponseEntity<>(order, HttpStatus.OK);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @GetMapping("/{userid}/orders/{orderid}")
  public ResponseEntity<Order> getOrderByOrderId(@PathVariable Long orderid) {
    try {
      Order order = orderService.getOrderById(orderid);
      return new ResponseEntity<>(order, HttpStatus.OK);
    } catch (OrderNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
