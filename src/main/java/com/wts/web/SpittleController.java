package com.wts.web;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import com.wts.model.SpittleModel;
import com.wts.service.SpittleNoticeService;
import com.wts.service.SpittleService;
import com.wts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wtswindows7 on 2017/2/4.
 */

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private UserService userService;

    private SpittleService spittleService;

    private SpittleNoticeService spittleNoticeService;

    @Autowired
    public SpittleController(UserService userService, SpittleService spittleService, SpittleNoticeService spittleNoticeService) {
        this.userService = userService;
        this.spittleService = spittleService;
        this.spittleNoticeService = spittleNoticeService;
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


    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publishSpittle(@ModelAttribute Spittle spittle, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
//        Set<Spittle> spittles = user.getSpittles();
//        for (Spittle spittle1 : spittles) {
//            System.out.println(spittle1.getContent());
//        }
        spittle.setUser(user);
        spittleService.publishSpittle(spittle);
        spittleNoticeService.noticeFriend(user, spittle);
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

    @RequestMapping(value = "/getFriendSpittles", method = RequestMethod.GET)
    public @ResponseBody
    List<SpittleModel> getFriendSpittles(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Spittle> spittles = spittleService.findFriendSpittles(user);
        List<SpittleModel> spittleModels = new ArrayList<>(spittles.size());
        for (int i = 0; i < spittles.size(); i++) {
            SpittleModel spittleModel = new SpittleModel();
            spittleModel.setId(spittles.get(i).getId());
            spittleModel.setUserid(spittles.get(i).getUser().getUserid());
            spittleModel.setContent(spittles.get(i).getContent());
            spittleModels.add(spittleModel);
        }
        return spittleModels;
    }

    @RequestMapping(value = "/readFriendSpittles/{pageNumber}", method = RequestMethod.GET)
    public @ResponseBody
    List<SpittleModel> readFriendSpittles(@PathVariable("pageNumber") Integer pageNumber, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Page<Spittle> spittlePage = spittleService.getFriendSpittles(user, pageNumber);
        List<Spittle> spittles = spittlePage.getContent();
        List<SpittleModel> spittleModels = new ArrayList<>(spittles.size());
        for (int i = 0; i < spittlePage.getContent().size(); i++) {
            SpittleModel spittleModel = new SpittleModel();
            spittleModel.setId(spittles.get(i).getId());
            spittleModel.setUserid(spittles.get(i).getUser().getUserid());
            spittleModel.setContent(spittles.get(i).getContent());
            spittleModels.add(spittleModel);
        }
        return spittleModels;
    }

}
