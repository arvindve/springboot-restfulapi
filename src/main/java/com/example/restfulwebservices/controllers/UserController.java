package com.example.restfulwebservices.controllers;

import com.example.restfulwebservices.entities.Post;
import com.example.restfulwebservices.entities.User;
import com.example.restfulwebservices.services.PostService;
import com.example.restfulwebservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    private UserService userService;

    private PostService postService;


    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Resource<User> getUserById(@PathVariable Integer id) {

        User user = userService.getUserById(id);
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Integer id) {
        System.out.println("Going to delete by id");
        userService.deleteUserById(id);
    }

    @PostMapping("/users")
    public Resource<User> createUser(@RequestBody User user) {

        userService.createUser(user);

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUserById(user.getId()));
        resource.add(linkTo.withRel("get-user"));
        return resource;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> getAllPostsById(@PathVariable Integer id) {

        User user = userService.getUserById(id);
        return user.getPosts();

    }

    @PostMapping("/users/{id}/posts")
    public Resource<User>  createPostForUser(@PathVariable Integer id, @RequestBody Post post) {

        User user = userService.getUserById(id);
        post.setUser(user);
        postService.createPost(post);

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllPostsById(user.getId()));
        resource.add(linkTo.withRel("all-posts"));
        return resource;


    }


}
