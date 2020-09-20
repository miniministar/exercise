package com.exercise.controller;

import com.exercise.util.HttpClientUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mogu/")
public class MoguController {

    @GetMapping("data")
    public String test() {
        String url = "https://api.mogu.com/h5/mwp.ferrari.searchActionLet/1/?data=%7B%22activityLaunchId%22%3A%2295%22%2C%22pageSize%22%3A18%2C%22appPlat%22%3A%22m%22%2C%22code%22%3A%22entranceMore%22%7D&mw-appkey=100028&mw-ttid=NMMain%40mgj_h5_1.0&mw-t=1600616724186&mw-uuid=3e482381-6a92-4853-a140-76c06279731a&mw-h5-os=iOS&mw-sign=16fee54df1d9945bb5fb09ae8833c859&callback=mwpCb1&_=1600616724190";
        String reponse = HttpClientUtil.doGet(url);
        return  reponse;
    }
}
