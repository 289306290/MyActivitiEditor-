package com.bolo.test.performer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("test")
    public Object test() {
        Map<String, String> resp = new HashMap<>();
        resp.put("say", "Hi");
        return resp;
    }

//    @GetMapping("editor")
//    public String editor() {
//        return "/modeler";
//    }

}
