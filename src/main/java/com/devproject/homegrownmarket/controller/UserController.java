package com.devproject.homegrownmarket.controller;

import com.devproject.homegrownmarket.entity.User;
import com.devproject.homegrownmarket.exceptions.UserNotFoundException;
import com.devproject.homegrownmarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class UserController {

    private final UserRepository repository;

    @GetMapping("/users")
    public Resources<Resource<User>> allUsers() {
        List<Resource<User>> users = repository.findAll().stream()
                .map(user -> new Resource<>(user,
                        linkTo(methodOn(UserController.class).selectUser(user.getUserId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).allUsers()).withRel("employees")))
                .collect(Collectors.toList());

        return new Resources<>(users,
                linkTo(methodOn(UserController.class).allUsers()).withSelfRel());
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public Resource<User> selectUser(@PathVariable Long id) {

        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).selectUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).allUsers()).withRel("users"));
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
                .orElseGet(() -> {
                    newUser.setUserId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
        return "User " + id + "removed";
    };

}


