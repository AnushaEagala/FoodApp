package com.example.resto.user;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

        private final UserService service;

        @PostMapping
        public ResponseEntity<?> changepwd(
                 @RequestBody ChangePwdRequest request,
                 Principal connecteduser
        ) {
                service.changepwd(request,connecteduser);
                return ResponseEntity.ok().build();
        }


    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest request,

                                        Principal connectedUser) {
        service.updateUser(request,connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cover", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadCoverPicture(
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Principal connecteduser    ) {

        service.uploadCoverPicture(file, connecteduser);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/image")
    public ResponseEntity<?> getimage( Principal connecteduser ) {
        String img =service.getimage(connecteduser);
        return ResponseEntity.ok(img);
    }
    }

