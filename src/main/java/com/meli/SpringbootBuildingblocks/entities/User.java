package com.meli.SpringbootBuildingblocks.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Entity
@Table(name = "user")
@JsonFilter("userFilter")
public class User extends RepresentationModel<User> {

  @Id
  @GeneratedValue
  private Long userId;

  @NotEmpty(message = "Username is Mandatory field, please provide username")
  @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
  private String username;

  @Size(min = 2, message = "Firstname must be atleast 2 character")
  @Column(name = "FIRST_NAME", length = 50, nullable = false)
  private String firstname;

  @Column(name = "LAST_NAME", length = 50, nullable = false)
  private String lastname;

  @Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
  private String email;

  @Column(name = "ROLE", length = 50, nullable = false)
  private String role;

  @Column(name = "SSN", length = 50, nullable = false, unique = true)
  //@JsonIgnore
  private String ssn;

  @OneToMany(mappedBy = "user")
  private List<Order> orders;

  // No Argument Constructor
  public User() {
  }

  // Fields Constructor
  public User(Long userId,
      String username,
      String firstname,
      String lastname,
      String email,
      String role,
      String ssn) {
    this.userId = userId;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.role = role;
    this.ssn = ssn;
  }

  // To String
  @Override
  public String toString() {
    return "User [id=" + userId
        + ", username=" + username
        + ", firstname=" + firstname
        + ", lastname=" + lastname
        + ", email=" + email
        + ", role=" + role
        + ", ssn=" + ssn + "]";
  }

}
