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

    // 글 등록
    @PostMapping
    public Post createPost(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("writer") String writer) {
        return postService.createPost(title, content, writer);
    }

    // 전체 글 조회
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    // ID를 통한 글 조회
    @GetMapping("/id/{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postService.getPostById(id);
    }

    // 제목을 통한 글 조회
    @GetMapping("/title/{title}")
    public Post getPostByTitle(@PathVariable("title") String title) {
        return postService.getPostByTitle(title);
    }

    // 작성자를 통한 글 조회
    @GetMapping("/writer/{writer}")
    public List<Post> getPostsByWriter(@PathVariable("writer") String writer) {
        return postService.getPostsByWriter(writer);
    }

    // 글 수정
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable("id") Long id,
                           @RequestParam("title") String title,
                           @RequestParam("content") String content) {
        return postService.updatePost(id, title, content);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }
}
