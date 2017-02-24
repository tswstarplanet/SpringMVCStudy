package com.wts.service;

import com.wts.domain.Friend;
import com.wts.domain.User;
import com.wts.repository.FriendRepository;
import com.wts.util.Constants;
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
        int status = 0;
        long actionId = user.getUserid();
        Friend friendEntity = new Friend(user, friend, status, actionId);
        return friendRepository.save(friendEntity);
    }

    @Override
    public List<Friend> findMyApplies(User user) {
        return friendRepository.findFriendByFriendAndStatus(user, Constants.FRIEND_RELATIONSHIP_APPLY);
    }

    @Override
    public Friend updateFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public Friend findFriendByUserAndFriend(User user, User friend) {
        return friendRepository.findFriendByUserAndFriend(user, friend);
    }
}
