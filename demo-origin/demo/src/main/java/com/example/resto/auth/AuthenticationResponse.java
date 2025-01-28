package com.example.resto.auth;

import com.example.resto.role.Role;
import com.example.resto.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
    private List<Role> roles;
    private String firstname;
    private String lastname;
    private String email;
    private int id;

}