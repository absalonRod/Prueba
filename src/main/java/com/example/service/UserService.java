package com.example.service;

import com.example.model.Book;
import com.example.model.BookRepository;
import com.example.model.user.User;
import com.example.model.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> user1 = repository.findById(id);
        if (user1.isPresent()) {
            user.setId(id);
            return repository.save(user);
        }
        throw new EntityNotFoundException(" " + id);
    }

    public boolean deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
