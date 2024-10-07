package com.example.springhw2.repository;

import com.example.springhw2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
//Post findById(Long id);
    Post findByTitle(String title);
    List<Post> findByWriter(String writer);
    Post findByContent(String content);

}
