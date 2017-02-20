package com.wts.web;

import com.wts.domain.Friend;
import com.wts.domain.User;
import com.wts.model.ApplyFriendModel;
import com.wts.model.MakeFriendModel;
import com.wts.model.MakeFriendResponse;
import com.wts.service.FriendService;
import com.wts.service.UserService;
import com.wts.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public void test() {
        System.out.println("hello");
    }

    @RequestMapping(value = "/makeFriend",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    MakeFriendResponse makeFriend(@RequestBody MakeFriendModel makeFriendModel, Authentication authentication) {
        MakeFriendResponse makeFriendResponse = new MakeFriendResponse();
        User friend = userService.findByUsername(makeFriendModel.getUsername());
        if (friend == null) {
            makeFriendResponse.setFlag(Constants.MAKE_FRIEND_APPLY_NOT_EXIST);
        } else {
            User user = userService.findByUsername(authentication.getName());
            if (friendService.makeFriendApply(user, friend) == null) {
                makeFriendResponse.setFlag(Constants.MAKE_FRIEND_APPLY_SEND_FAIL);
            } else {
                makeFriendResponse.setFlag(Constants.MAKE_FRIEND_APPLY_SEND_SUCCESS);
            }
        }
        return makeFriendResponse;
    }

    @RequestMapping(value = "getMyFriends", method = RequestMethod.GET)
    public @ResponseBody
    List<ApplyFriendModel> getMyFriendApplies(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        List<Friend> friends = friendService.findMyApplies(user);
        List<ApplyFriendModel> applyFriendModels = new ArrayList<>();
        for (Friend friend : friends) {
            ApplyFriendModel applyFriendModel = new ApplyFriendModel();
            applyFriendModel.setId(friend.getUser().getUserId());
            applyFriendModel.setUsername(friend.getUser().getUsername());
            applyFriendModels.add(applyFriendModel);
        }
        return applyFriendModels;
    }

}
