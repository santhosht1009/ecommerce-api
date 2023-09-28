package com.example.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce_api.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
public Users findByEmail(String email);
}
