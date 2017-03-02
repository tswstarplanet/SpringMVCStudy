package com.wts.service;

import com.wts.domain.Spittle;
import com.wts.domain.User;

/**
 * Created by weitaosheng on 2017/3/2.
 */
public interface SpittleNoticeService {

    void noticeFriend(User user, Spittle spittle);

}
