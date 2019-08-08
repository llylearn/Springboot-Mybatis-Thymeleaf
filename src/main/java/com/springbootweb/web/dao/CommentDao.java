package com.springbootweb.web.dao;

import com.springbootweb.web.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommentDao {
//    拿出最多6条评论展示
    @Select("select * from Comment limit 6")
    Collection<Comment> getComments();

//    插入一条评论
    @Insert("insert into Comment(username, topic) values(#{username}, #{topic})")
    void insertComment(Comment comment);

//    查找对应username的所有评论
    @Select("select * from Comment where username=#{username}")
    Collection<Comment> getCommentsByUsername(String username);
//    删除指定的comment
    @Delete("delete from Comment where topic=#{topic}")
    void deleteComment(String topic);
//    修改指定评论
}
