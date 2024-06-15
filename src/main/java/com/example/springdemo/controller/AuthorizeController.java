package com.example.springdemo.controller;

import com.example.springdemo.dto.AccessTokenDTO;
import com.example.springdemo.dto.GithubUser;
import com.example.springdemo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("Ov23linnb3tCEsDnkIuC");
        accessTokenDTO.setClient_secret("6d3e0121990544618068331575ccfe55cf72f972");
        accessTokenDTO.setRedirect_url("https://localhost:8887/callback");
        accessTokenDTO.setState(state);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "hello";
    }
}
