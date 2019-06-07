package com.example.blogspring.controller;

import com.example.blogspring.model.Post;
import com.example.blogspring.repository.CategoryRepository;
import com.example.blogspring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String main(ModelMap modelMap){
        List<Post> allPosts = postRepository.findAll();
        modelMap.addAttribute("posts", allPosts);
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }
}
