package com.springbootweb.web.service;

import com.springbootweb.web.dao.CommentDao;
import com.springbootweb.web.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
//    查找6条comments
    public Collection<Comment> getComments(){ return commentDao.getComments();}
//    插入一条评论
    public void  insertComment(Comment comment){ commentDao.insertComment(comment);}
//    查找对应username的所有comments
    public Collection<Comment> getCommentsByUsername(String username){ return commentDao.getCommentsByUsername(username);}
//     删除对应的comment
    public void deleteComment(String topic){ commentDao.deleteComment(topic);
        System.out.println(topic);}
//    修改对应的comment(待实现)

//    刷新页面，从新取回数据
    public void refreshPage(Map map, String username){
        map.put("comments", getComments());
        map.put("personalComments", getCommentsByUsername(username));
        map.put("loginStatu", "true");
    }
}
