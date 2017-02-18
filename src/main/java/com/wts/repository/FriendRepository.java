package com.wts.repository;

import com.wts.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by weitaosheng on 2017/2/18.
 */

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Friend save(Friend friend);

}
