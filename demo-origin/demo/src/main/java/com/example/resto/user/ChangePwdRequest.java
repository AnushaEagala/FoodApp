package com.example.resto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePwdRequest {
    private String currentpwd;
    private String newpwd;
    private String confirmpwd;

}
