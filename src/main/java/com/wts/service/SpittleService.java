package com.wts.service;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by wtswindows7 on 2017/2/6.
 */
public interface SpittleService {
    void publishSpittle(Spittle spittle);
    List<Spittle> readMySpittles(User user);
    List<Spittle> findFriendSpittles(User user);

    Page<Spittle> getFriendSpittles(User user, Integer pageNumber);
}
