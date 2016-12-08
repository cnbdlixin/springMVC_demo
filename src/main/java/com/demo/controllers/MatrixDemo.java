package com.demo.controllers;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * URI 矩阵模式
 *
 * Created by codingH on 2016/12/8.
 */
@Controller
@RequestMapping("matrix")
public class MatrixDemo {

    /**
     * 使用{@link MatrixVariable}传递矩阵变量
     *
     * Get http://localhost:8080/matrix/simple/133;sim=test
     */
    @RequestMapping("simple/{id}")
    public @ResponseBody String simple(@PathVariable int id, @MatrixVariable String sim){
        return id + ":" + sim;
    }

    /**
     * 使用{@link MatrixVariable}传递名称相同的矩阵变量
     * 注意:在方法的参数中,id和sec是不必须出现的
     *
     * Get http://localhost:8080/matrix/simple/133;sim=test/144;sim=test2
     */
    @RequestMapping("simple/{id}/{sec}")
    public @ResponseBody String simple(@MatrixVariable(name = "sim",pathVar = "id") String sim,
                                       @MatrixVariable(name = "sim",pathVar = "sec") String secSim){
        return "first sim:" + sim + " and sec is:" + secSim;
    }

    /**
     * 使用Map<String,String>作为参数类型,获取请求中的URI矩阵
     *
     * Get http://localhost:8080/matrix/map/133;sim=11;xx=1/map2/144;sim=22;rr=2
     * @return {"sim":"11","xx":"1","rr":"2"}=={"sim":"22","rr":"2"}
     *
     * 注意:这里first中sim的值,只能获取到map后的,而map2的无法获取
     */
    @RequestMapping("map/{id}/map2/{sec}")
    public @ResponseBody String mapEx(@MatrixVariable Map<String,String> first,
                                       @MatrixVariable(pathVar = "sec") Map<String,String> sec){
        Gson gson = new Gson();
        String firsts = gson.toJson(first);
        String secs = gson.toJson(sec);
        return firsts + "==" +secs;
    }


    /**
     * 官方示例.
     * 使用MultiValueMap<String, String>作为参数类型,获取请求中的URI矩阵
     *
     * Get http://localhost:8080/matrix/owners/42;q=11;r=12/pets/21;q=22;s=23
     * @return {"q":["11","22"],"r":["12"],"s":["23"]}=={"q":["22"],"s":["23"]}
     *
     * 注意:这里matrixVars中q的值,可以获取到{ownerId}和{petId}中的q
     * {@link this.mapEx}
     */
    @GetMapping("owners/{ownerId}/pets/{petId}")
    public @ResponseBody String findPet(
            @MatrixVariable MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar="petId") MultiValueMap<String, String> petMatrixVars) {

            // matrixVars: ["q" : [11,22], "r" : 12, "s" : 23]
            // petMatrixVars: ["q" : 11, "s" : 23]
        Gson gson = new Gson();
        String firsts = gson.toJson(matrixVars);
        String secs = gson.toJson(petMatrixVars);
        return firsts + "==" +secs;
}
}
