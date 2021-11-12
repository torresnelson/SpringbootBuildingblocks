package com.meli.SpringbootBuildingblocks.exceptions;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorDetails {

  private Date timestamp;
  private String message;
  private String errorDetails;

}
