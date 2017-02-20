package com.wts.web;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import com.wts.service.SpittleService;
import com.wts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

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
//        Set<Spittle> spittles = user.getSpittles();
//        for (Spittle spittle1 : spittles) {
//            System.out.println(spittle1.getContent());
//        }
        spittle.setUser(user);
        spittleService.publishSpittle(spittle);
        return "redirect:/spittles/mySpittles";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test() {
        System.out.println("test");
    }

    @RequestMapping(value = "/mySpittles", method = RequestMethod.GET)
    public ModelAndView getMySpittles(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Spittle> spittleList = spittleService.readMySpittles(user);
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("mySpittleList", spittleList);
        return modelAndView;
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
