package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.CommentService;
import com.csye6220.finalProject.service.PostService;
import com.csye6220.finalProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/post/")
public class PostController {


    private final PostService postService;
    private final CommentService commentService;


    public PostController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/showcreate")
    public ModelAndView showCreateForm(){

        return new ModelAndView("createPost");
    }

    @PostMapping("/create")
    public String createPost(@RequestParam("postName") String postName, @RequestParam("url") String url, @RequestParam("description") String description, HttpSession session) {

        String token = (String) session.getAttribute("token");
        if(token != null){
            String username = JwtProvider.getUserNameFromJWT(token);
            PostDto postDto = new PostDto(postName,url,description);
            Post post = postService.createPost(postDto, username);
            if(post != null){
                return "postSuccess";
            }else{
                return "error";
            }
        }else{
            return "login";
        }

    }

    @GetMapping("/all")
    public String getAllPost(Model model){

        try{
            List<Post> posts = postService.getAllPosts();
            for(Post p : posts){
                System.out.println("Post name : " + p.getPostId());
                List<Comment> comments = p.getComments();
                for(Comment c : comments){
                    System.out.println(c.getText());
                }

            }
            model.addAttribute("posts", posts);
            return "posts";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    @GetMapping("/id/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable long postId){
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable Long postId,@RequestParam("postDesc") String postDesc, @RequestParam("url") String url, @RequestParam("postName") String postName){
        PostDto postDto = new PostDto(postName, url, postDesc);
        postService.updatePost(postId, postDto);
        return "redirect:/api/myprofile";
    }


    @GetMapping("/postbyuser")
    public ModelAndView getPostsByUser(HttpSession session){
        String token = (String) session.getAttribute("token");
        String username = JwtProvider.getUserNameFromJWT(token);
        List<Post> posts = postService.getPostByUsername(username);
        for(Post p :posts){
            System.out.println(p.getUser());
        }
        ModelAndView mav = new ModelAndView("userPosts");
        mav.addObject("posts", posts);
        return mav;
    }

    @PostMapping("/delete/{postId}")
    public String deletePostById(@PathVariable long postId){
        postService.deletePost(postId);
        System.out.println("I am here");
        return "redirect:/api/myprofile";
    }

}
