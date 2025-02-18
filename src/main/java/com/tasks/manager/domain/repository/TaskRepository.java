package com.tasks.manager.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.manager.domain.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}