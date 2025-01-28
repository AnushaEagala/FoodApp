package com.example.resto.user;

import com.example.resto.user.file.FileStorageService;
import com.example.resto.user.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repo;
    private final FileStorageService fileStorageService;

    public void changepwd(ChangePwdRequest request, Principal connecteduser) {
        var user = (User)((UsernamePasswordAuthenticationToken)connecteduser).getPrincipal();

       if(! passwordEncoder.matches(request.getCurrentpwd(), user.getPassword())){
           throw new IllegalStateException("wrong pwd");
       }
       if(!request.getNewpwd().equals(request.getConfirmpwd())){
           throw new IllegalStateException("not same pwd");
       }

       user.setPassword(passwordEncoder.encode(request.getNewpwd()));
       repo.save(user);
    }

    public void updateUser(UpdateUserRequest request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
       // var profilePicture = fileStorageService.saveFile(file, user.getId());
     //   user.setPhoto(profilePicture);
        repo.save(user);
    }

    public void uploadCoverPicture(MultipartFile file, Principal connecteduser) {
        var user = (User)((UsernamePasswordAuthenticationToken)connecteduser).getPrincipal();
        var profilePicture = fileStorageService.saveFile(file, user.getId());
        user.setPhoto(profilePicture);
        repo.save(user);

    }

    public String getimage(Principal connecteduser) {
        var user = (User)((UsernamePasswordAuthenticationToken)connecteduser).getPrincipal();
     //   byte[] img = FileUtils.readFileFromLocation(user.getPhoto());
       System.out.println(user.getPhoto());
        return user.getPhoto();

    }


}
