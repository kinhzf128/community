package com.kinhzf128.community.controller;

import com.kinhzf128.community.dto.QuestionDto;
import com.kinhzf128.community.dto.QuestionPageDto;
import com.kinhzf128.community.mapper.QuestionMapper;
import com.kinhzf128.community.mapper.UserMapper;
import com.kinhzf128.community.model.Question;
import com.kinhzf128.community.model.User;
import com.kinhzf128.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author kinhzf128
 * @Date 2020/5/26 15:50
 */
@Controller
public class IndexController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionService questionService;


    @GetMapping("/")
    public String hello(HttpServletRequest request, Model model, QuestionPageDto questionPageDto){
        //判断cookies中是否有用户存在
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
        //判断是否有当前页码 赋值
        if (questionPageDto.getPage()<=0){
            questionPageDto.setPage(1);
        }
        if (questionPageDto.getPageCount()==0){
            questionPageDto.setPageCount(5);
        }
        questionPageDto = questionService.selectAll(questionPageDto);

        model.addAttribute("questions",questionPageDto);
        return "index";
    }
}
