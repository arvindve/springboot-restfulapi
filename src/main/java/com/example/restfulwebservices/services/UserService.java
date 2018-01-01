package com.example.restfulwebservices.services;

import com.example.restfulwebservices.entities.Post;
import com.example.restfulwebservices.entities.User;
import com.example.restfulwebservices.exception.UserNotFoundException;
import com.example.restfulwebservices.repository.PostRepository;
import com.example.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("id : " + id);
        }

        return optionalUser.get();

    }

    public void deleteUserById(Integer id) {

        userRepository.deleteById(id);

    }

    public User createUser(User user) {

        return userRepository.save(user);

    }


}
