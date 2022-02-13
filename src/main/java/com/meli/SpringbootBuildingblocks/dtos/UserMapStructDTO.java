package com.meli.SpringbootBuildingblocks.dtos;

public class UserMapStructDTO {

  private Long userId;
  private String username;

  public UserMapStructDTO() {
  }

  public UserMapStructDTO(Long userId, String username, String email) {
    super();
    this.userId = userId;
    this.username = username;
    this.email = email;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String email;

}
