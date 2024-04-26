package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.model.Comment;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);

    }
    @GetMapping("/allusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestParam("email") String email) {
        userService.updateUser(email, id);
        return "redirect:/api/myprofile";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/myprofile")
    public ModelAndView getUserWithPosts(HttpSession session){
        String token = (String) session.getAttribute("token");
        if(token == null){
            session.setAttribute("notAuth", true);
            return new ModelAndView("redirect:/api/auth/showLogin");
        }
        String username = JwtProvider.getUserNameFromJWT(token);
        User user = userService.getUserByUsername(username);
        List<Post> myposts = user.getPosts();
        for(Post p : myposts){
            System.out.println(p.getPostName());
            System.out.println(p.getDescription());
            List<Comment> comments = p.getComments();
            for(Comment c: comments){
                c.getText();
                System.out.println(c.getText());
            }
        }
        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("profile", user);
        mav.addObject("myposts", myposts);
        return mav;
    }

    @PostMapping("/join/{communityId}")
    public String joinCommunity(@PathVariable long communityId, HttpSession session){
        String token = (String) session.getAttribute("token");
        if(token == null){
            session.setAttribute("notAuth", true);
            return "redirect:/api/auth/showLogin";
        }
        String username = JwtProvider.getUserNameFromJWT(token);
        userService.joinCommunity(username, communityId);
        return "redirect:/api/community/postcomm/" +communityId;
    }

    @PostMapping("/leave/{communityId}")
    public String leaveCommunity(@PathVariable long communityId, HttpSession session){
        String token = (String) session.getAttribute("token");
        if(token == null){
            session.setAttribute("notAuth", true);
            return "redirect:/api/auth/showLogin";
        }
        String username = JwtProvider.getUserNameFromJWT(token);
        userService.leaveCommunity(username, communityId);
        return "redirect:/api/community/postcomm/"+communityId;
    }



    @GetMapping("/updatePasswordForm")
    public String showUpdatePasswordForm(){
        return "updatePassword";
    }

    @PostMapping("/updatePassword/")
    public String updatePassword(@RequestParam("currentPassword") String currPass, @RequestParam("newPassword") String newPass, HttpSession session){
        String token = (String) session.getAttribute("token");
        String username = JwtProvider.getUserNameFromJWT(token);
        userService.updatePassword(currPass, newPass, username);
        return "redirect:/api/myprofile";
    }

    @GetMapping("/updateProfileForm")
    public ModelAndView showUpdateProfileForm(@RequestParam("userId") Long userId){
        ModelAndView mav = new ModelAndView("updateProfile");
        mav.addObject("userId", userId);
        return mav;
    }

}
