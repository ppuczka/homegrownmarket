package com.devproject.homegrownmarket.controller;

import com.devproject.homegrownmarket.entity.User;
import com.devproject.homegrownmarket.exceptions.UserNotFoundException;
import com.devproject.homegrownmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class UserController {

    private final UserRepository repository;


    @GetMapping("/users")
    public List<User> allUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User selectUser(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return repository.findById(id).map(user -> {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setActive(Optional.ofNullable(newUser.getActive()).orElse(1));
            user.setPassword(Optional.ofNullable(newUser.getPassword()).orElse(user.getPassword()));
            return repository.save(user);
        })
                .orElseGet(() -> {newUser.setUserId(id);
                    return repository.save(newUser);
                });
    }

}


