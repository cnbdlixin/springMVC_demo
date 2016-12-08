package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by codingH on 2016/12/8.
 */
@RequestMapping("restcon")
@RestController
public class RestControllerDemo {

    @RequestMapping("sim")
    public String sim(){
        return "sim";
    }
}
