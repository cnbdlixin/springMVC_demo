package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
