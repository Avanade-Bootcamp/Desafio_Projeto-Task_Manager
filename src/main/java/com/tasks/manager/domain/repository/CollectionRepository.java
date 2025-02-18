package com.tasks.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.manager.domain.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    
}