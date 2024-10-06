package com.example.springhw2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Post {

    // TODO : 글 제목, 본문, 작성자, 글 작성 시간, 글 수정 시간

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private String writer;

    private Date createdAt;

    private Date updatedAt;

}
