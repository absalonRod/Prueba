package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Book;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long> {
}
