package com.example.resto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
}