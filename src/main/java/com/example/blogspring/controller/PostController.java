package com.example.blogspring.controller;

import com.example.blogspring.model.Post;
import com.example.blogspring.repository.CategoryRepository;
import com.example.blogspring.repository.PostRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String addPostView(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\User\\IdeaProjects\\BlogSpring\\image\\" + name);
        multipartFile.transferTo(file);
        post.setImageUrl(name);
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/byCategory")
    public String byCategory(@RequestParam("categoryId") int categoryId, ModelMap modelMap) {
       // modelMap.addAttribute("posts", postRepository.findPostByCategory(categoryId));
        return "postsByCategory";
    }

    @GetMapping("/getImage")
    public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imageUrl") String imageUrl) throws IOException {
        InputStream in = new FileInputStream("C:\\Users\\User\\IdeaProjects\\BlogSpring\\image\\" + imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
