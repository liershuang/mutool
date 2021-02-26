package com.mutool.job;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * 描述：<br>
 * 作者：les<br>
 * 日期：2020-09-24 11:55<br>
 */
@Controller
public class TestController {

    @PostConstruct
    public void loadTest() {
        System.out.println("load ************");
    }

    @RequestMapping("test")
    public String testController() {
        return "test.html";
    }

}
