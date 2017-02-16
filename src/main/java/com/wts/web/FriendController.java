package com.wts.web;

import com.wts.domain.User;
import com.wts.model.MakeFriendModel;
import com.wts.model.MakeFriendResponse;
import com.wts.service.FriendService;
import com.wts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by weitaosheng on 2017/2/15.
 */

@Controller
@RequestMapping("/friend")
public class FriendController {

    private UserService userService;

    private FriendService friendService;

    @Autowired
    public FriendController(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    @RequestMapping(value = "/makeFriend", method = RequestMethod.POST)
    public @ResponseBody
    MakeFriendResponse makeFriend(@RequestBody MakeFriendModel makeFriendModel, Authentication authentication) {
        MakeFriendResponse makeFriendResponse = new MakeFriendResponse();
        User friend = userService.findByUsername(makeFriendModel.getUsername());
        if (friend == null) {
            makeFriendResponse.setExist(false);
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (friendService.makeFriendApply(user, friend) == false) {
                makeFriendResponse.setExist(false);
            } else {
                makeFriendResponse.setExist(true);
            }
        }
        return makeFriendResponse;
    }
}
