package com.springbootweb.web.controller;

import com.springbootweb.web.entity.Comment;
import com.springbootweb.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/submitComm")
    public String insertComment(Comment comment, Map<String, Object> map){
        //已经拿到session,就可以拿到session中保存的用户信息了。
        String username = getSessionUsername();
        comment.setUsername(username);
        commentService.insertComment(comment);
        commentService.refreshPage(map, username);
        return "mainPage";
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("topic") String topic, Map<String, Object> map){
        commentService.deleteComment(topic);
        System.out.println(topic);
        String username = getSessionUsername();
        commentService.refreshPage(map, username);
        return "mainPage";
    }

    public String getSessionUsername(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (String )request.getSession().getAttribute("username");
    }
}
