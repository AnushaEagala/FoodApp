package com.example.resto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String role; // Now only a single role as String
}
