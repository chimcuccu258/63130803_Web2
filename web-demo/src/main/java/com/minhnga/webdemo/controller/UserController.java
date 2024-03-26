package com.minhnga.webdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * @created 26/03/2024 - 09:36
 * @project web-demo
 * @author TaosDev
 */
@Controller
public class UserController {
   @RequestMapping("/")
    public String user() {
        return "Home";
    }
}
