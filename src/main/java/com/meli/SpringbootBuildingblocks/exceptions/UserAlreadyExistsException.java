package com.meli.SpringbootBuildingblocks.exceptions;

public class UserAlreadyExistsException extends Exception {

  private static final long serialVersionUID = -4707794253099202986L;

  public UserAlreadyExistsException(String message) {
    super(message);
  }

}
