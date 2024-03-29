package com.springbootweb.web.config;

import com.springbootweb.web.service.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    //实现WebMvcConfigurer类
        //添加视图映射，用来防止表单多次提交
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            //从新定向
            registry.addViewController("/index.html").setViewName("index");
            registry.addViewController("/main.html").setViewName("mainPage");
        }
//      拦截器，直接访问某些地址会被拦截(如何解除静态资源被拦截问题有待解决)
        @Override
        public void addInterceptors(InterceptorRegistry registry){
            registry.addInterceptor(new AccountService()).addPathPatterns("/main.html");
//                    .excludePathPatterns("/","/static/**"，"/index.html","/login");
        }
}
