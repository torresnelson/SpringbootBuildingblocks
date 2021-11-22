package com.meli.SpringbootBuildingblocks.services;

import com.meli.SpringbootBuildingblocks.entities.User;
import com.meli.SpringbootBuildingblocks.exceptions.UserAlreadyExistsException;
import com.meli.SpringbootBuildingblocks.exceptions.UserNotFoundException;
import com.meli.SpringbootBuildingblocks.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(User user) throws UserAlreadyExistsException {
    User existingUser = userRepository.findByUsername(user.getUsername());
    if (existingUser != null) {
      throw new UserAlreadyExistsException("User already exist.");
    }
    return userRepository.save(user);
  }

  public Optional<User> getUserById(Long id) throws UserNotFoundException {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new UserNotFoundException("User Not found in user repository");
    }
    return user;
  }

  public User updateUserById(Long id, User user) throws UserNotFoundException {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      throw new UserNotFoundException("User Not found in repository, provide the correct user id");
    }
    user.setUserId(id);
    return userRepository.save(user);
  }

  public void deleteUserById(Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "User Not found in repository, provide the correct user id");
    }
    userRepository.deleteById(id);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}
