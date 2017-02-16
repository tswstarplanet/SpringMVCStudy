package com.wts.service;

import com.wts.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by weitaosheng on 2017/2/16.
 */

@Service
public class FriendServiceImpl implements FriendService {
    @Override
    public boolean makeFriendApply(User user, User friend) {
        return user.getFriends().add(friend);
    }
}
