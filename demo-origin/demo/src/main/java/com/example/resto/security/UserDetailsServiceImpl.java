package com.example.resto.security;

import com.example.resto.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        return repo.findByEmail(useremail)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
