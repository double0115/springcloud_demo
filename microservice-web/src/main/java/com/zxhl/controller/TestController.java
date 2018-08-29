package com.zxhl.controller;

import com.zxhl.test.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(String name){
        int result = testService.addUser();
        return "success===="+name;
    }
}
