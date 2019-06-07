package com.example.blogspring.repository;

import com.example.blogspring.model.Category;
import com.example.blogspring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    //List<Category> findPostByCategory(int categoryId);
}
