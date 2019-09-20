package com.devproject.homegrownmarket.utils;

import com.devproject.homegrownmarket.controller.UserController;
import com.devproject.homegrownmarket.entity.User;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>> {

    @Override
    public Resource toResource(User user) {
        return new Resource<>(user,
                linkTo(methodOn(UserController.class).selectUser(user.getUserId())).withSelfRel(),
                linkTo(methodOn(UserController.class).allUsers()).withRel("users"));
    }
}

