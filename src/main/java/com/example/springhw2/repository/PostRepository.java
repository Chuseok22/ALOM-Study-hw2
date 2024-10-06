package com.example.springhw2.repository;

import com.example.springhw2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findByPostId(Long Id);
    Post findByTitle(String title);
    Post findByWriter(String writer);
    Post findByContent(String content);

}
