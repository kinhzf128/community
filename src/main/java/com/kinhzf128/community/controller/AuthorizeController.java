package com.kinhzf128.community.controller;

import com.kinhzf128.community.dto.AccessTokenDto;
import com.kinhzf128.community.dto.GithubUser;
import com.kinhzf128.community.provider.GithubProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @GetMapping("/callback")
    public GithubUser callback(AccessTokenDto accessTokenDto){
        accessTokenDto.setClient_id(client_id);
        accessTokenDto.setClient_secret(client_secret);
        accessTokenDto.setRedirect_uri(redirect_uri);
        String accessToken = githubProvide.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvide.getGithubUser(accessToken);
        return githubUser;

    }

}
