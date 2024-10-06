package com.example.springhw2.controller;

import com.example.springhw2.entity.Post;
import com.example.springhw2.service.PostService;
import lombok.Getter;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
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
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/post/id/{id}")
    public Post findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @GetMapping("/post/title/{title}")
    public Post findByTitle(@PathVariable("title") String title) {
        return postService.findByTitle(title);
    }

    @GetMapping("/post/writer/{writer}")
    public Post findByWriter(@PathVariable("writer") String writer) {
        return postService.findByWriter(writer);
    }

    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable("id") Long id,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content) {

        return postService.updatePost(id,title,content);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
    }
}
