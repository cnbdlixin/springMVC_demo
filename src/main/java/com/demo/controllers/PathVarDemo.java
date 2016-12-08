package com.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 关于{@link PathVariable URI模版}在springmvc中使用
 *
 * Created by codingH on 2016/12/8.
 */
@Controller
@RequestMapping("pathvar/{classVar}")
public class PathVarDemo {

    /**
     * 使用@{@link PathVariable} 创建一个路径中包含了变量的方法
     */
    @RequestMapping(path = "simple/{pathvar}")
    public @ResponseBody
    String usePathVar(@PathVariable String pathvar){
        return pathvar;
    }

    /**
     * 使用{@link PathVariable}修饰Map<String,String>类型的参数,参数会被自动填充至map中
     */
    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping(path = "map/{pathvar}")
    public @ResponseBody
    String userMapPathVar(@PathVariable Map<String,String> pa){
        return pa.get("pathvar");
    }


    /**
     * 使用{@link PathVariable}获取修饰在class和method中RequestMapping的变量
     */
    @RequestMapping(path = "getClassAndMethodVar/{methodVar}")
    public @ResponseBody
    String getClassAndMethodVar(@PathVariable Map<String,String> pa,@PathVariable String classVar,@PathVariable String methodVar){
        String _classvar = pa.get("classVar");
        String _methodvar = pa.get("methodVar");

        String result = "_classvar:";
        result += _classvar + "..";
        result += "_methodvar:";
        result += _methodvar +"..";
        result += "classVar:";
        result += classVar +"..";
        result += "methodVar:";
        result += methodVar +"..";

        return result;
    }


}
