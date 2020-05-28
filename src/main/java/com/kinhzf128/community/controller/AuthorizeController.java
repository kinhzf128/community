package com.kinhzf128.community.controller;

import com.kinhzf128.community.dto.AccessTokenDto;
import com.kinhzf128.community.dto.GithubUser;
import com.kinhzf128.community.mapper.UserMapper;
import com.kinhzf128.community.model.User;
import com.kinhzf128.community.provider.GithubProvide;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author kinhzf128
 * @Date 2020/5/26 20:26
 */
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvide githubProvide;
    //从配置文件中找对应的key把value赋值
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(AccessTokenDto accessTokenDto,HttpServletResponse response){
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvide.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvide.getGithubUser(accessToken);
        User user=new User();
        user.setName(githubUser.getName());
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAvatarUrl(githubUser.getAvatarUrl());
        String token=UUID.randomUUID().toString();
        user.setToken(token);
        if (githubUser!=null){
            // 登录成功
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else {
            // 登录失败
            return "redirect:/";
        }

    }

}
