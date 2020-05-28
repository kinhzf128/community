package com.kinhzf128.community.controller;

import com.kinhzf128.community.mapper.QuestionMapper;
import com.kinhzf128.community.mapper.UserMapper;
import com.kinhzf128.community.model.Question;
import com.kinhzf128.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

/**
 * @author kinhzf128
 * @Date 2020/5/27 17:14
 */
@Controller
public class PublishController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        User user=null;
        if (cookies!=null){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    user=userMapper.findByToken(cookie.getValue());
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);

        }else {
            model.addAttribute("error","未登录，请登录");
        }
        return "redirect:/";
    }
}
