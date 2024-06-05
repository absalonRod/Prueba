package com.example.controllers;

import com.example.model.Book;
import com.example.model.user.User;
import com.example.service.BookService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService ;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> books = userService.getAllUsers();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService .getUserById(id);
        return user.map(user1 -> new ResponseEntity<>(user1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService .saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> user1 = userService.getUserById(id);
        if (user1.isPresent()) {
            User user2 = userService.updateUser(id, user);
            return new ResponseEntity<>(user2, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUSer(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
