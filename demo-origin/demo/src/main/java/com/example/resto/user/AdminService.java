package com.example.resto.user;


import com.example.resto.role.Role;
import com.example.resto.role.RoleRepository;
import com.example.resto.user.file.FileStorageService;
import com.example.resto.user.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;
    private final FileStorageService fileStorageService;

    public void updateUser(Integer id, AdminUpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
       // var profilePicture = fileStorageService.saveFile(file, user.getId());
      //  user.setPhoto(profilePicture);

        // Properly managing roles
        if (!user.getRoles().isEmpty()) {
            user.getRoles().clear(); // Clear existing roles to avoid UnsupportedOperationException
        }
        Role newRole = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found: " + request.getRole()));
        user.getRoles().add(newRole); // Add new role

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        // Retrieve the user with the tokens
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if user has any tokens and delete them
        tokenRepository.deleteAllByUser(user);

        // Now it's safe to delete the user
        userRepository.delete(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role updateRole(Integer roleId, RoleUpdateRequest request) {
        return roleRepository.findById(roleId)
                .map(role -> {
                    role.setName(request.getName());
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new IllegalArgumentException("Role not found with ID: " + roleId));
    }

    @Transactional
    public void deleteRole(Integer roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new IllegalArgumentException("Role not found with ID: " + roleId);
        }
        roleRepository.deleteById(roleId);
    }

    public Role addRole(RoleUpdateRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        return roleRepository.save(role);
    }

    public List<Role> getRolesWithNoUsers() {
        return roleRepository.findRolesWithNoUsers();
    }


    @Transactional
    public void blocuser(Integer id) {
        // Retrieve the user with the tokens
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(user.isEnabled()){
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
    }

    public void uploadCoverPicture(MultipartFile file, Principal connecteduser) {
        var user = (User)((UsernamePasswordAuthenticationToken)connecteduser).getPrincipal();
        var profilePicture = fileStorageService.saveFile(file, user.getId());
        user.setPhoto(profilePicture);
        userRepository.save(user);

    }

    public byte[] getimage(Principal connecteduser) {
        var user = (User)((UsernamePasswordAuthenticationToken)connecteduser).getPrincipal();
        byte[] img = FileUtils.readFileFromLocation(user.getPhoto());
        return img;

    }
}
