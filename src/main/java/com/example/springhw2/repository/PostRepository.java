package com.example.springhw2.repository;

import com.example.springhw2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    List<Post> findByWriter(String writer);
}
