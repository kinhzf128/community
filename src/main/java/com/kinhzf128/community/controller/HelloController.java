package com.kinhzf128.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

/**
 * @author kinhzf128
 * @Date 2020/5/26 15:50
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@PathParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
