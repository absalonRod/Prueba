package com.example.service;

import com.example.model.Book;
import com.example.model.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    public Book saveBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Optional<Book> book1 = repository.findById(id);
        if (book1.isPresent()) {
            book.setId(id);
            return repository.save(book);
        }
        throw new EntityNotFoundException(" " + id);
    }

    public boolean deleteBook(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
