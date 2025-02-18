package com.tasks.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.manager.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}