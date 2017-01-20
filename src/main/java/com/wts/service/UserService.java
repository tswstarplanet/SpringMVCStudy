package com.wts.service;

import com.wts.domain.User;

/**
 * Created by wtswindows7 on 2017/1/20.
 */
public interface UserService {

    public void save(User user);

    public User findByUsername(String username);
}
