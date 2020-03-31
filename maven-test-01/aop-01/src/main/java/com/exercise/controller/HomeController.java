package com.exercise.controller;

import com.exercise.aop.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

//    @LogAnnotation(actiondese="用户登录",actionvalue=LogAnnotation.Logtype.LOGIN)
    @MyLog(value="用户登录",type= MyLog.Logtype.LOGIN)
    @RequestMapping("/select")
    public @ResponseBody String selectUser() {
        System.out.println("select");
        return "select";
    }

    @RequestMapping("/update")
    public String updateUser() {
        System.out.println("update");
        return "update";
    }

    @RequestMapping("/insert")
    public String insertUser() {
        System.out.println("insert");

        return "insert";
    }

    @RequestMapping("/delete")
    public String deleteUser() {
        System.out.println("delete");
        return "delete";
    }
}
