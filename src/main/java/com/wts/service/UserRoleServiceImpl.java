package com.wts.service;

import com.wts.domain.UserRole;
import com.wts.domain.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by weitaosheng on 2017/1/22.
 */
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public void save(UserRole userRole) {

    }
}
