package com.kinhzf128.community.controller;

import com.kinhzf128.community.mapper.UserMapper;
import com.kinhzf128.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * @author kinhzf128
 * @Date 2020/5/26 15:50
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String hello(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    User user=userMapper.findByToken(cookie.getValue());
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
