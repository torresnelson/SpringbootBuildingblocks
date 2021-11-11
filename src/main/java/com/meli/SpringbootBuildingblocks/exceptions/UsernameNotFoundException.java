package com.meli.SpringbootBuildingblocks.exceptions;

public class UsernameNotFoundException extends Exception {

  private static final long serialVersionUID = -294054593013438242L;

  public UsernameNotFoundException(String message) {
    super(message);
  }
}
