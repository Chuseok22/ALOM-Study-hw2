package com.example.springhw2.service;

import com.example.springhw2.entity.Post;
import com.example.springhw2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post post(String title, String content, String writer) {
        if (title == null || content == null)
            throw new IllegalArgumentException("Title and content cannot be null");

        Post post = new Post();

        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setPostedAt(LocalDateTime.now());
        post.setModifiedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.findByTitle(title);
        if (post == null)
            throw new NoSuchElementException();
        return post;
    }

    public List<Post> getPostByWriter(String writer) {
        List<Post> posts = postRepository.findByWriter(writer);
        if (posts == null || posts.isEmpty())
            throw new NoSuchElementException();
        return posts;
    }

    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElseThrow(NoSuchElementException::new);

        post.setTitle(title);
        post.setContent(content);
        post.setModifiedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
