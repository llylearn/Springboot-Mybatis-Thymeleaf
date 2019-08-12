package com.springbootweb.web.controller;

import com.springbootweb.web.entity.Account;
import com.springbootweb.web.entity.Comment;
import com.springbootweb.web.service.AccountService;
import com.springbootweb.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class AccountController  {
//    visit index page
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
//    post method for login
    //create loginService object
    @Autowired
    AccountService accountService;
    @Autowired
    CommentService commentService;
//  登录响应方法
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String,Object> map,  HttpSession session){

        accountService.verifyIdentity(username,password, map);
        if ("success".equals(map.get("msg"))){
            //if succeed, add user attribute to session
            session.setAttribute("username", username);
        }
        return (String)map.get("url");
    }
//  修改账号密码的响应方法
    @PostMapping("/modifyPsw")
    public String modifyPsw(@RequestParam("newPsw") String newPsw, @RequestParam("oldPsw") String oldPsw, Map<String, Object> map, Account account){
        String username = getSessionUsername();
        accountService.verifyIdentity(username, oldPsw, map);
        accountService.modifyPswAssisstant(map, account, username, newPsw);
        return "mainPage";
    }
//  获取session里面面的username属性的值
    public String getSessionUsername(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (String )request.getSession().getAttribute("username");
    }
}