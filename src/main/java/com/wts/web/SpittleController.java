package com.wts.web;

import com.wts.domain.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wtswindows7 on 2017/2/4.
 */

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String publishSpittle(Spittle spittle) {
        System.out.println("hello");
        return "403";
    }

}
