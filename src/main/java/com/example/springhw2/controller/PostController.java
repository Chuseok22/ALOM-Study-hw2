package com.example.springhw2.controller;

import com.example.springhw2.entity.Post;
import com.example.springhw2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {this.postService = postService;}

    //글 작성
    @PostMapping("")
    public ResponseEntity<Post> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("writer") String writer) {
        Post post = postService.createPost(title, content, writer);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
        //201 Created 상태 정보와 Body를 같이 return
    }

    //모든 글 조회
    @GetMapping("")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
        //200 ok 상태 반환
    }

    //Id를 통한 글 조회
    @GetMapping("/id/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    //title을 통한 조회
    @GetMapping("/title/{title}")
    public ResponseEntity<Post> getPostByTitle(@PathVariable("title") String title) {
        Post post = postService.findByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    //wrtier를 통한 조회
    @GetMapping("/writer/{writer}")
    public ResponseEntity<List<Post>> getPostByWriter(@PathVariable("writer") String writer) {
        List<Post> posts = postService.findByWriter(writer);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    //제목, 내용 수정
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        Post post = postService.editPost(id, title, content);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    //글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
        //204 Not Found 상태 return
    }
}
