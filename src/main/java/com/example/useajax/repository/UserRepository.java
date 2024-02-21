package com.example.useajax.repository;

import com.example.useajax.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUsername(String username);
}
