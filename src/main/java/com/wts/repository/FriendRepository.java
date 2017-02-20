package com.wts.repository;

import com.wts.domain.Friend;
import com.wts.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weitaosheng on 2017/2/18.
 */

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Friend save(Friend friend);

    List<Friend> findFriendByFriendAndStatus(User friend, int status);

}
