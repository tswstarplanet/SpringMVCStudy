package com.wts.service;

import com.wts.domain.Spittle;
import com.wts.domain.User;

import java.util.List;

/**
 * Created by wtswindows7 on 2017/2/6.
 */
public interface SpittleService {
    void publishSpittle(Spittle spittle);
    List<Spittle> readMySpittles(User user);
}
