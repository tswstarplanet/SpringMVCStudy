package com.wts.repository;

import com.wts.domain.Friend;
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

    @Query("select userid, friendid, actionid from friend where (userid = ?1 or friendid = ?1) and status = 0 and actionid != ?1")
    List<Friend> findMyApplies(long id);

}
