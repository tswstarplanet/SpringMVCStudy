package com.wts.service;

import com.wts.domain.User;

/**
 * Created by weitaosheng on 2017/2/16.
 */
public interface FriendService {
    boolean makeFriendApply(User user, User friend);
}
