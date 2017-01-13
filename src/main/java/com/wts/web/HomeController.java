package com.wts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wtswindows7 on 2017/1/13.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

}
