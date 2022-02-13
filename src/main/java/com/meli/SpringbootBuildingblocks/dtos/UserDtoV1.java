package com.meli.SpringbootBuildingblocks.dtos;

import com.meli.SpringbootBuildingblocks.entities.Order;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDtoV1 {

  private Long userId;

  private String username;

  private String firstname;

  private String lastname;

  private String email;

  private String role;

  private String ssn;

  private List<Order> orders;

}
