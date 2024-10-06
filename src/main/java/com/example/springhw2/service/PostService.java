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

    public Post createPost(String title, String content, String writer) {
        if (title.isBlank() || content.isBlank()) {
            throw new IllegalArgumentException("제목과 본문은 비어있을 수 없습니다.");
        }

        Post newpost = new Post();

        newpost.setTitle(title);
        newpost.setContent(content);
        newpost.setWriter(writer);
        newpost.setCreatedAt(LocalDateTime.now());
        newpost.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(newpost);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));
    }

    public Post findByTitle(String title) {
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 제목을 가진 글이 존재하지 않습니다."));

    }

    public Post findByWriter(String writer) {
        return postRepository.findByWriter(writer)
                .orElseThrow(() -> new IllegalArgumentException("해당 글 작성자가 존재하지 않습니다."));
    }

    public Post updatePost(Long id, String title, String content) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));

        updatePost.setTitle(title);
        updatePost.setContent(content);
        updatePost.setUpdatedAt(LocalDateTime.now());

        return updatePost;
    }

    public void deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));

        postRepository.deleteById(id);
    }
}
