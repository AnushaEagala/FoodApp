package com.example.resto.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Integer> {

Optional<Role> findByName(String role);

    @Query("SELECT r FROM Role r WHERE r.users IS EMPTY")
    List<Role> findRolesWithNoUsers();



}
