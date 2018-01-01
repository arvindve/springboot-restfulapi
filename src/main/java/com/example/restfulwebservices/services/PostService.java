package com.example.restfulwebservices.services;

import com.example.restfulwebservices.entities.Post;
import com.example.restfulwebservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public void createPost(Post post){
        postRepository.save(post);
    }
}
