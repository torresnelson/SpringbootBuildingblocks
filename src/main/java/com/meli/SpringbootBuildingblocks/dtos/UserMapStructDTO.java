package com.meli.SpringbootBuildingblocks.dtos;

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
public class UserMapStructDTO {

  private Long userId;

  private String username;

  private String email;

}
