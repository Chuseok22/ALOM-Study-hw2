package com.example.springhw2.controller;

import com.example.springhw2.entity.Post;
import com.example.springhw2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/post") 배운 거 한 번 써봄
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public Post createPost(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("writer") String writer) {

        return postService.createPost(title, content, writer);
    }

    @GetMapping("/post")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post/id/{id}")
    public Post findPostById(@PathVariable("id") Long id) {
        return postService.findPostById(id);
    }

    @GetMapping("/post/title/{title}")
    public Post findPostByTitle(@PathVariable("title") String title) {
        return postService.findPostByTitle(title);
    }

    @GetMapping("/post/writer/{writer}")
    public Post findPostByWriter(@PathVariable("writer") String writer) {
        return postService.findPostByWriter(writer);
    }

    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable("id") Long id,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content) {

        return postService.updatePost(id, title, content);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }
}
