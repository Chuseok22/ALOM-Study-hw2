package com.example.springhw2.service;

import com.example.springhw2.entity.Post;
import com.example.springhw2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //CRUD
    //Post Create
    public Post createPost(String title, String content, String writer) {

        //title또는 content가 null이면 예외처리
        if(title == null || content == null || title.isEmpty()) {
            throw new IllegalArgumentException("title and content cannot be null");
        }

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        post.setPostDate(LocalDateTime.now());
        post.setEditedDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    //Post Read
    //모든 글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //id로 조회
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Id에 해당하는 Post가 없습니다."));
        //findById return 값이 Optional<Post> 이므로 Post 객체를 반환하기 위해서
        //orElseThrow() 예외처리를 던어 로직에서 예외를 처리 하기 편하게 만듬
    }

    //title로 조회
    public Post findByTitle(String title) {
        return postRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Title에 해당하는 Post가 없습니다."));
    }

    //writer로 조회
    public List<Post> findByWriter(String writer) {
        return postRepository.findByWriter(writer);
    }

    //Post Update
    //게시글 수정
    //수정과 삭제 부분은 Transactional 어노테이션을 사용해서
    //자동으로 롤백해주는 기능을 추가
    @Transactional
    public Post editPost(Long id, String title, String content) {
        Post post = findById(id);
        post.setTitle(title);
        post.setContent(content);
        post.setEditedDate(LocalDateTime.now());

        return postRepository.save(post);
    }

    //Post Delete
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
