package com.example.springboot3todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot3todoapp.models.TodoItem;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
    
}
