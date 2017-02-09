package com.wts.web;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import com.wts.service.SpittleService;
import com.wts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wtswindows7 on 2017/2/4.
 */

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    @Autowired
    private UserService userService;

    @Autowired
    private SpittleService spittleService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishSpittle(@ModelAttribute Spittle spittle, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        spittle.setUser(user);
        spittleService.publishSpittle(spittle);
        return "welcome";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SpittleService getSpittleService() {
        return spittleService;
    }

    public void setSpittleService(SpittleService spittleService) {
        this.spittleService = spittleService;
    }
}
