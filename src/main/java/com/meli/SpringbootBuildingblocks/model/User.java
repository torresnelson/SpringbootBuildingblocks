package com.meli.SpringbootBuildingblocks.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    private String ssn;

    @Column(name = "FIRST_NAME",length = 50, nullable = false)
    private String firstname;

    @Column(name = "LAST_NAME",length = 50, nullable = false)
    private String lastname;

    @Column(name = "EMAIL_ADDRESS",length = 50, nullable = false)
    private String email;

    @Column(name = "ROLE",length = 50, nullable = false)
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", ssn='" + ssn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
