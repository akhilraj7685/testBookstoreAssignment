package com.ak.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.example.entity.Author;

public interface AuthorRepo  extends JpaRepository<Author, Integer>{

}
