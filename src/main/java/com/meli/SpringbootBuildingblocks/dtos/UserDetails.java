package com.meli.SpringbootBuildingblocks.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDetails {

  private String firstname;

  private String lastname;

  private String city;

}
