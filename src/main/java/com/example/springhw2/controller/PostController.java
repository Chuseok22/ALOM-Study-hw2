package com.example.springhw2.controller;

import com.example.springhw2.entity.Post;
import com.example.springhw2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post post(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer) {
        return postService.post(title, content, writer);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/id/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/title/{title}")
    public Post getPostByTitle(@PathVariable("title") String title) {
        return postService.getPostByTitle(title);
    }

    @GetMapping("/writer/{writer}")
    public List<Post> getPostByWriter(@PathVariable("writer") String writer) {
        return postService.getPostByWriter(writer);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("content") String content) {
        return postService.updatePost(id, title, content);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }

}
