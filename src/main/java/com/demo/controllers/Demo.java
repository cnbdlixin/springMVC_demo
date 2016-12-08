package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 01172973 on 2016/11/1.
 */
@Controller
@RequestMapping("demo")
public class Demo {

    @RequestMapping("hello")
    public @ResponseBody String hello(){
        return "hello";
    }


    /**
     * 通过{@link RequestContextUtils} 获取 {@link WebApplicationContext}
     * @param request
     *              包含了请求的很多信息
     */
    @RequestMapping("getWeb")
    public @ResponseBody void getWebApplicationContext(HttpServletRequest request) {
        WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(request);
        System.out.println(webApplicationContext);

        //DispatcherServlet 维护了一个默认的列表,其中保存了所有bean的默认实现,在org\springframework\web\servlet\DispatcherServlet.properties里
    }

}
