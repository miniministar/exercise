package com.exercise.demo.mvc.action;

import com.exercise.demo.service.IDemoService;
import com.exercise.framework.annotation.Autowired;
import com.exercise.framework.annotation.Controller;
import com.exercise.framework.annotation.QueryParam;
import com.exercise.framework.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/demo")
public class DemoAction {

    @Autowired
    private IDemoService demoService;

    @RequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @QueryParam("name") String name) {
        String str = demoService.get(name);
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/add")
    public Integer add(@QueryParam("a") Integer a, Integer b) {
        return  a + b;
    }
}
