package com.example.resto.user;


import com.example.resto.role.Role;
import com.example.resto.user.file.FileUtils;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminUserService;
    private final UserService userService;



    @PostMapping("/changepwd")
    public ResponseEntity<?> changepwd(
            @RequestBody ChangePwdRequest request,
            Principal connecteduser
    ) {
        userService.changepwd(request,connecteduser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = adminUserService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,

                                        @RequestBody AdminUpdateUserRequest request) {
        adminUserService.updateUser(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        adminUserService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = adminUserService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody RoleUpdateRequest request) {
        Role updatedRole = adminUserService.updateRole(id, request);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        adminUserService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/roles")
    public ResponseEntity<Role> addRole(@RequestBody RoleUpdateRequest request) {
        Role role = adminUserService.addRole(request);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/roles/unlinked")
    public ResponseEntity<List<Role>> getUnlinkedRoles() {
        List<Role> roles = adminUserService.getRolesWithNoUsers();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/bloc/{id}")
    public ResponseEntity<?> blocuser(@PathVariable Integer id) {
        adminUserService.blocuser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cover", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadCoverPicture(
            @Parameter()
            @RequestPart("file") MultipartFile file,
            Principal connecteduser    ) {

        adminUserService.uploadCoverPicture(file, connecteduser);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/image")
    public ResponseEntity<?> getimage( Principal connecteduser ) {
        byte[] img =adminUserService.getimage(connecteduser);
        return ResponseEntity.ok(img);
    }
}
