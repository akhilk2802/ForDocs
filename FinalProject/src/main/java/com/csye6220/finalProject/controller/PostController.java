package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.PostDto;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.model.Community;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.CommentService;
import com.csye6220.finalProject.service.CommunityService;
import com.csye6220.finalProject.service.PostService;
import com.csye6220.finalProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/post/")
public class PostController {


    private final PostService postService;
    private final CommentService commentService;
    private final CommunityService communityService;


    public PostController(PostService postService, UserService userService, CommentService commentService, CommunityService communityService) {
        this.postService = postService;
        this.commentService = commentService;
        this.communityService = communityService;
    }

    @GetMapping("/showcreate")
    public String showCreateForm(HttpSession session, Model model, @RequestParam(required = false) Long communityId){
        String token = (String) session.getAttribute("token");
        if(token != null){
            String username = JwtProvider.getUserNameFromJWT(token);
            model.addAttribute("communityId", communityId);
            Community community = null;
            if(communityId != null){
                boolean isMember = false;
                community = communityService.findById(communityId);
                List<User> members = community.getMembers();
                for(User u : members){
                    if(u.getUsername().equals(username)){
                        isMember = true;
                    }
                }
                if(!isMember){
                    session.setAttribute("notMember", true);
                    return "redirect:/api/community/postcomm/" + communityId;
                }
                return "createPost";
            }
            return"createPost";
        }else{
            session.setAttribute("notAuth", true);
            return "redirect:/api/auth/showLogin";
        }
    }


    @PostMapping("/create")
    public String createPost(@RequestParam("postName") String postName, @RequestParam("url") String url, @RequestParam("description") String description, @RequestParam(required = false) Long communityId ,HttpSession session) {

        String token = (String) session.getAttribute("token");
        if(token != null){
            String username = JwtProvider.getUserNameFromJWT(token);
            PostDto postDto = new PostDto(postName,url,description);

            Post post = postService.createPost(postDto, username, communityId);
            if(post != null){
                return "redirect:/home";
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
            List<Community> communityList = communityService.getAll();
            for(Post p : posts){
                System.out.println("Post name : " + p.getPostId());
                List<Comment> comments = p.getComments();
                for(Comment c : comments){
                    System.out.println(c.getText());
                    System.out.println(c.getUser().getUsername());
                }

            }
            model.addAttribute("posts", posts);
            model.addAttribute("communities", communityList);
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
