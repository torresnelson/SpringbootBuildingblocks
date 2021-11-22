package com.meli.SpringbootBuildingblocks.services;

import com.meli.SpringbootBuildingblocks.entities.Order;
import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.OrderNotFoundException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.OrderRepository;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  private final UserRepository userRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
  }


  public List<Order> getAllOrders(Long userid) throws UserNotFoundException {
    Optional<User> user = userRepository.findById(userid);
    if (!user.isPresent()) {
      throw new UserNotFoundException("User not found");
    }
    return user.get().getOrders();
  }

  public void createOrder(Long userid, Order order) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findById(userid);
    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException("User not found.");
    }
    User user = optionalUser.get();
    order.setUser(user);
    orderRepository.saveAndFlush(order);
  }

  public Order getOrderById(Long orderid) throws OrderNotFoundException {
    Optional<Order> optionalOrder = orderRepository.findById(orderid);
    if (!optionalOrder.isPresent()) {
      throw new OrderNotFoundException("Order not found");
    }
    return optionalOrder.get();
  }
}
