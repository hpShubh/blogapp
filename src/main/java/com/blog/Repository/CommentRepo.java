package com.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
