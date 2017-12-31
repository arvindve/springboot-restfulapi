package com.example.restfulwebservices.repository;

import com.example.restfulwebservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
