package com.example.springhw2.service;

import com.example.springhw2.entity.Post;
import com.example.springhw2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    //게시물 등록
    public Post register(String title,String content,String writer){
        if(title==null||content==null) {
            throw new NullPointerException("널입니다");
        }else {
            Post post = new Post();
            post.setTitle(title);
            post.setContent(content);
            post.setWriter(writer);
            post.setCreatedAt(LocalDateTime.now());
            post.setFixedAt(LocalDateTime.now());
            return postRepository.save(post);
        }}
    //게시물 전부 조회
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    //Id를 통한 조회
    public Post findByPostId(Long Id){
        return postRepository
                .findById(Id)
                .orElseThrow();
    }

    //제목을 통한 조회
    public Post findByPostTitle(String title){
        return postRepository.findByTitle(title);
    }
    //작성자 통한 조회
    public List<Post> findByPostWriter(String writer){
        return postRepository.findByWriter(writer);
    }
    //글 수정
    public Post changePost(Long id,String title,String content){
        Post post = postRepository
                .findById(id)
                .orElseThrow();
        post.setTitle(title);
        post.setContent(content);
        post.setFixedAt(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    //글 삭제
    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);

    }




}
