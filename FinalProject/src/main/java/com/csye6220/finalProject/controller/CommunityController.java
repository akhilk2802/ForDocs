package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.exception.ResourceNotFoundException;
import com.csye6220.finalProject.model.Community;
import com.csye6220.finalProject.model.Post;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.CommunityService;
import com.csye6220.finalProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/community/")
public class CommunityController {
    private final CommunityService communityService;
    private final UserService userService;

    public CommunityController(CommunityService communityService, UserService userService) {
        this.communityService = communityService;
        this.userService = userService;
    }

    @GetMapping("/showCommunityForm")
    public String showCommunityForm(HttpSession session){
        String token = (String) session.getAttribute("token");
        if(token != null){
            return "createCommunity";
        }else{
            session.setAttribute("notAuth",true);
            return "redirect:/api/auth/showLogin";
        }

    }
    @PostMapping("/create")
    public String create(@RequestParam("name") String name, @RequestParam("desc") String desc, HttpSession session){

        String token = (String) session.getAttribute("token");
        String username = JwtProvider.getUserNameFromJWT(token);
        try{
            communityService.create(name, desc, username);
            return "redirect:/api/post/all";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/getall")
    public ModelAndView getAllCommunities(){
        List<Community> communityList= communityService.getAll();
        ModelAndView mav = new ModelAndView("commList");
        mav.addObject("commlist", communityList );
        return mav;
    }

    @GetMapping("/postcomm/{commid}")
    public ModelAndView getPostsCommunitiesById(@PathVariable long commid, HttpSession session){
        String token = (String) session.getAttribute("token");
        String username = null;
        if (token != null) {
            username = JwtProvider.getUserNameFromJWT(token);
        }
        User user = null;
        if (username != null) {
            user = userService.getUserByUsername(username);
        }
        List<Post> postsbycommunity= communityService.getPostsByCommunityId(commid);
        Community community = communityService.findById(commid);
        List<User> members = community.getMembers();
        ModelAndView mav = new ModelAndView("community");
        mav.addObject("postsbycommunity", postsbycommunity);
        mav.addObject("community", community);
        mav.addObject("members", members);
        mav.addObject("currentUser", user);
        return mav;
    }

    @GetMapping("/getcomm/{commId}")
    public ModelAndView getCommunityById(@PathVariable long commId){
        Community community = communityService.findById(commId);
        ModelAndView mav = new ModelAndView("postSuccess");
        mav.addObject("community", community);
        return mav;
    }

    @GetMapping("/members/{communityId}")
    public ModelAndView getCommunityMembers(@PathVariable long communityId){
        Community community = communityService.findById(communityId);
        ModelAndView mav = new ModelAndView("membersOfComm");
        if(community != null){
            if(community.getMembers() == null){
                community.setMembers(new ArrayList<>());
            }
            List<User> members = community.getMembers();
            mav.addObject("members", members);
            return mav;
        }else{
            throw new ResourceNotFoundException("Not found");
        }
    }

}
