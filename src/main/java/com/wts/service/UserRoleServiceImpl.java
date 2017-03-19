package com.wts.service;

import com.wts.annotation.UserRoleServiceAnnotate;
import com.wts.domain.UserRole;
import com.wts.domain.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by weitaosheng on 2017/1/22.
 */

@Service
@UserRoleServiceAnnotate
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Override
    public void save(UserRole userRole) {
        userRolesRepository.save(userRole);
    }
}
