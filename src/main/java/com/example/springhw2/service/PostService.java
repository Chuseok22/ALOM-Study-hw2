package com.example.springhw2.service;

import com.example.springhw2.entity.Post;
import com.example.springhw2.repository.PostRepository;
import lombok.NonNull;
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

    public Post createPost(@NonNull String title, @NonNull String content, @NonNull String writer) {

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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(@NonNull Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));
    }

    public Post findPostByTitle(@NonNull String title) {
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 제목을 가진 글이 존재하지 않습니다."));

    }

    public Post findPostByWriter(@NonNull String writer) {
        return postRepository.findByWriter(writer)
                .orElseThrow(() -> new IllegalArgumentException("해당 글 작성자가 존재하지 않습니다."));
    }

    public Post updatePost(@NonNull Long id, @NonNull String title, @NonNull String content) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));

        if (title.isBlank() || content.isBlank()) {
            throw new IllegalArgumentException("제목과 본문은 비어있을 수 없습니다.");
        }

        // 변경 사항이 없는 경우
        if (title.equals(updatePost.getTitle()) && content.equals(updatePost.getContent())) {
            throw new IllegalStateException("업데이트할 내용이 없습니다.");
        }

        updatePost.setTitle(title);
        updatePost.setContent(content);
        updatePost.setUpdatedAt(LocalDateTime.now());

        return updatePost;
    }

    public void deletePost(@NonNull Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다."));

        postRepository.deleteById(id);
    }
}
