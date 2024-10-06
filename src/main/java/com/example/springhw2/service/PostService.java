package com.example.springhw2.service;

import com.example.springhw2.entity.Post;
import com.example.springhw2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(String title, String content, String writer) {

        if (title == null || content == null || title.isBlank() || content.isBlank()) {
            throw new IllegalArgumentException("제목과 본문은 null 이거나 비어있을 수 없습니다.");
        }

        Post newpost = new Post();

        newpost.setTitle(title);
        newpost.setContent(content);
        newpost.setWriter(writer);
        newpost.setCreatedAt(LocalDateTime.now());
        newpost.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(newpost);
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(Long id) {
        Objects.requireNonNull(id, "id 값이 null 일 수 없습니다.");
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 id를 가진 글이 존재하지 않습니다."));
    }

    public Post findPostByTitle(String title) {
        Objects.requireNonNull(title, "title 값이 null 일 수 없습니다.");

        return postRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("해당 제목을 가진 글이 존재하지 않습니다."));

    }

    public List<Post> findPostByWriter(String writer) {
        Objects.requireNonNull(writer, "writer 값이 null 일 수 없습니다.");

        return postRepository.findByWriter(writer)
                .orElseThrow(() -> new NoSuchElementException("해당 작성자의 글이 존재하지 않습니다."));
    }

    public Post updatePost(Long id, String title, String content) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 id를 가진 글이 존재하지 않습니다."));

        if (title == null || content == null || title.isBlank() || content.isBlank()) {
            throw new IllegalArgumentException("제목과 본문은 null 이거나 비어있을 수 없습니다.");
        }

        // 변경 사항이 없는 경우
        if (title.equals(updatePost.getTitle()) && content.equals(updatePost.getContent())) {
            throw new IllegalStateException("업데이트할 내용이 없습니다.");
        }

        updatePost.setTitle(title);
        updatePost.setContent(content);
        updatePost.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(updatePost);
    }

    public void deletePost(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 id를 가진 글이 존재하지 않습니다."));

        postRepository.deleteById(id);
    }
}
