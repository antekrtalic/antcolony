package com.example.antcolony.dto;

import lombok.Data;

@Data
public class PeopleDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String verifyPassword;
}
