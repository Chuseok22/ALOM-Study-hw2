package com.example.springhw2.controller;

import com.example.springhw2.entity.Post;
import com.example.springhw2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }
    //글 작성
    @PostMapping("")
    public Post register(@RequestParam("title") String title,
                         @RequestParam("content")String content,
                         @RequestParam("writer")String writer){
        return postService.register(title, content, writer);
    }
//전체 글 조회
    @GetMapping("")
    public List<Post> findAll(){
        return postService.findAll();
    }
    //Id를 통한 글 조회
    @GetMapping("/id/{id}")
    public Post findPostById(@PathVariable("id")Long id){
        return postService.findByPostId(id);
    }
    // 제목을 통한 글 조회
    @GetMapping("/title/{title}")
    public Post findPostByTitle(@PathVariable("title")String title){
        return postService.findByPostTitle(title);
    }
    //작성자를 통한 글 조회
    @GetMapping("/writer/{writer}")
    public Post findPostByWriter(@PathVariable("writer")String writer){
        return postService.findByPostWriter(writer);
    }
    //글 수정
    @PutMapping("/post/{id}")
    public Post changePost(@PathVariable("id")Long id,@RequestParam("title")String title,@RequestParam("content")String content){
        return postService.changePost(id,title,content);

    }

    //글 삭제
    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable("id")Long id){
        postService.deletePost(id);
        return "ok";
    }

}
