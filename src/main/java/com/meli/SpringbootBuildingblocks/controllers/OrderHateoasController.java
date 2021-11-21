package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.exceptions.OrderNotFoundException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.OrderService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

  private final OrderService orderService;

  @Autowired
  public OrderHateoasController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/{userid}/orders")
  public List<Order> getAllOrders(@PathVariable Long userid) {
    try {
      return orderService.getAllUsers(userid);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
