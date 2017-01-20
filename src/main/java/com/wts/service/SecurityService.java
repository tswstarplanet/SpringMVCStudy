package com.wts.service;

/**
 * Created by wtswindows7 on 2017/1/20.
 */
public interface SecurityService {

    public String findLoggedInUsername();

    public void autoLogin(String username, String password);

}
