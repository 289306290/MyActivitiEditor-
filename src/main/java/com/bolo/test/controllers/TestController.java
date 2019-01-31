package com.bolo.test.controllers;

import com.bolo.test.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private TestMapper testMapper;

    @RequestMapping("test")
    public Object test() {
        Map<String, String> resp = new HashMap<>();
        resp.put("show databases", testMapper.showDatabases().toString());
        return resp;
    }

//    @GetMapping("editor")
//    public String editor() {
//        return "/modeler";
//    }

}
