package com.example.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    Admin findByUsername(String username);
}