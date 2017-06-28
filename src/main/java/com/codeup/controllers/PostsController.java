package com.codeup.controllers;


import com.codeup.Repositories.PostsRepository;
import com.codeup.Repositories.UsersRepository;
import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


@Controller
public class PostsController {

    private final PostSvc postSvc;
    private final UsersRepository usersDao;


    @Autowired
    public PostsController(PostSvc postSvc, UsersRepository usersDao) {
        this.postSvc = postSvc;
        this.usersDao = usersDao;

    }

    //=====add pageable=====//
    @Autowired
    private PostsRepository posts;

    @GetMapping("/posts/")
    public String viewAll(Model model, @PageableDefault(value = 10) Pageable pageable) {
        model.addAttribute("page", posts.findAll(pageable));
        return "posts/index";
    }

    //=====pageable=====//

// changed from method below to pageable //
//    @GetMapping("/posts")
//    public String viewAll(Model model) {
//        Iterable<Post> posts = postSvc.findAll();
//        model.addAttribute("posts", posts);
//        return "posts/index";
//    }

    @GetMapping("/posts.json")
    public @ResponseBody
    Iterable<Post> viewAllPosts() {
        return postSvc.findAll();
    }

    @GetMapping("/posts/ajax")
    public String viewAllPostsUsingAnAjaxCall() {
        return "posts/ajax";
    }


    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        // Inside the method that shows an individual post, create a new post object and pass it to the view.
        Post post = postSvc.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(
            @Valid Post post,
            Errors validation,
            Model model
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            @RequestParam(name = "file") MultipartFile uploadedFile,
            return "posts/create";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setOwner(user);
        postSvc.save(post);
        model.addAttribute("post", post);
        return "posts/create";
    }


//Edit Functionality//

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post post = postSvc.findOne(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post post) {
        postSvc.save(post);
        return "redirect:/posts/" + post.getId();

    }
    String filename = uploadedFile.getOriginalFilename();
    String filepath = Paths.get(uploadPath, filename).toString();
    File destinationFile = new File(filepath);

        try {
        uploadedFile.transferTo(destinationFile);
//            model.addAttribute("message", "File successfully uploaded!");
    } catch (IOException e) {
        e.printStackTrace();
        model.addAttribute("message", "Oops! Something went wrong! " + e);
    }

    @PostMapping("/posts/delete")
    public String deletePost(@ModelAttribute Post post,
                             @RequestParam long id,
                             Model model) {
        postSvc.deletePost(id);
        model.addAttribute("message", "Your post was deleted correctly");
        return "redirect:/posts";
    }


}