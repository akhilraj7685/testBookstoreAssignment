package com.ak.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ak.example.entity.Book;

public interface BookRepo  extends JpaRepository<Book, Integer>{

}
