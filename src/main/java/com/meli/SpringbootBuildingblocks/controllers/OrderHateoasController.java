package com.meli.SpringbootBuildingblocks.controllers;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.services.OrderHateoasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateoasController {

  private final OrderHateoasService orderHateoasService;

  @Autowired
  public OrderHateoasController(OrderHateoasService orderHateoasService) {
    this.orderHateoasService = orderHateoasService;
  }

  @GetMapping("/{userid}/orders")
  public CollectionModel<Order> getAllOrders(@PathVariable Long userid) {
    try {
      return orderHateoasService.getAllOrders(userid);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
