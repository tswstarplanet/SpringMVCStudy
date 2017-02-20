package com.wts.service;

import com.wts.domain.Friend;
import com.wts.domain.User;
import com.wts.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weitaosheng on 2017/2/16.
 */

@Service
public class FriendServiceImpl implements FriendService {

    private FriendRepository friendRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public Friend makeFriendApply(User user, User friend) {
        User user1 = user.getUserId() < friend.getUserId() ? user : friend;
        User friend1 = user.getUserId() > friend.getUserId() ? user : friend;
        int status = 0;
        long actionId = user.getUserId();
        Friend friendEntity = new Friend(user1, friend1, status, actionId);
        return friendRepository.save(friendEntity);
    }

    @Override
    public List<Friend> findMyApplies(Long id) {
        return friendRepository.findMyApplies(id);
    }
}
