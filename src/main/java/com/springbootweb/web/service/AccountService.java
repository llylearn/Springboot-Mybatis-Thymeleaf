package com.springbootweb.web.service;

import com.springbootweb.web.dao.AccountDao;
import com.springbootweb.web.dao.CommentDao;
import com.springbootweb.web.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AccountService implements HandlerInterceptor {
    @Autowired
    AccountDao accountDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    CommentService commentService;
    public Account getAccount(String username){
        return accountDao.getAccoByUsername(username);
    }
    public int checkAccoExist(String username){
        return accountDao.checkAccoByUsername(username);
    }
    public void modifyPsw(Account account) { accountDao.modifyPsw(account);}

    public void verifyIdentity(String username, String password, Map map){
        if (!"".equals(username)&& checkAccoExist(username)>0){
            Account account = getAccount(username);
            if (password.equals(account.getPassword())){
                map.put("comments", commentDao.getComments());
                map.put("personalComments", commentDao.getCommentsByUsername(username));
                map.put("msg", "success");
                map.put("loginStatu", "true");
                map.put("url", "mainPage");
            }else {
                map.put("msg", "请输入正确的用户名和密码");
                map.put("url", "index");
            }
        }else {
            map.put("msg", "请输入正确的用户名和密码");
            map.put("url", "index");
        }
    }

    public void modifyPswAssisstant(Map map, Account account, String username, String newPsw){
        if ("success".equals(map.get("msg"))&&!"".equals(newPsw)){
            account.setUsername(username);
            account.setPassword(newPsw);
            modifyPsw(account);
        }else {
            map.put("modifyErr", "修改失败");
            commentService.refreshPage(map, username);
        }
    }


    //Interceptor
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("username");
        if (user != null){
            return true;
        }else {
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
