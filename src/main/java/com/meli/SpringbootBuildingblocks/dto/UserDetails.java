package com.meli.SpringbootBuildingblocks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetails {

  private String firstname;
  private String lastname;
  private String city;

  @Override
  public String toString() {
    return "UserDetails [firstname = " + firstname + ", lastname = " + lastname + ", City = " + city
        + "]";
  }
}
