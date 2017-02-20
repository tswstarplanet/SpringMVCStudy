package com.wts.service;

import com.wts.domain.Friend;
import com.wts.domain.User;

import java.util.List;

/**
 * Created by weitaosheng on 2017/2/16.
 */
public interface FriendService {
    Friend makeFriendApply(User user, User friend);

    List<Friend> findMyApplies(User user);
}
