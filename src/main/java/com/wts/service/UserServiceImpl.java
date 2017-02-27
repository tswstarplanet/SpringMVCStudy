package com.wts.service;

import com.wts.domain.User;
import com.wts.domain.UserRepository;
import com.wts.domain.UserRole;
import com.wts.domain.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wtswindows7 on 2017/1/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRolesRepository userRolesRepository;

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void register(User user) {
        long id = save(user).getUserid();
        UserRole userRole = new UserRole();
        userRole.setUserid(id);
        userRole.setRole("ROLE_USER");
        userRolesRepository.save(userRole);
    }

    @Override
    public User updateOnlineStatus(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUserid(long userid) {
        return userRepository.findByUserid(userid);
    }
}
