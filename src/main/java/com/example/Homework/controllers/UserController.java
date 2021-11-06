package com.example.Homework.controllers;

import com.example.Homework.models.User;
import com.example.Homework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping
    public @ResponseBody List<User> getUsers() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        return new ResponseEntity<>(repository.save(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getOneUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public @ResponseBody User updateUser(@PathVariable Long id,
                                          @RequestBody User updates) {
        User user = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(updates.getName() != null) user.setName(updates.getName());
        if(updates.getEmail() != null) user.setEmail(updates.getEmail());
        if(updates.getLanguages() != null) user.setLanguages(updates.getLanguages());

        return repository.save(user);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }
}
