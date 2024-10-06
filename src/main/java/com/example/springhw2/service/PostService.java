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
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 글 등록
    public Post createPost(String title, String content, String writer) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    // 전체 글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ID를 통한 글 조회
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // 제목을 통한 게시글 조회
    public Post getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    // 작성자를 통한 게시글 조회
    public List<Post> getPostsByWriter(String writer) {
        return postRepository.findByWriter(writer);
    }

    // 글 수정
    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        post.setTitle(title);
        post.setContent(content);
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    // 글 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
