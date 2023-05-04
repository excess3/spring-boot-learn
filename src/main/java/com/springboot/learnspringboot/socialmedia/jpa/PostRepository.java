package com.springboot.learnspringboot.socialmedia.jpa;

import com.springboot.learnspringboot.socialmedia.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
