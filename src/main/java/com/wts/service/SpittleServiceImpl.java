package com.wts.service;

import com.wts.domain.Friend;
import com.wts.domain.Spittle;
import com.wts.domain.SpittleNotice;
import com.wts.domain.User;
import com.wts.repository.SpittleNoticeRepository;
import com.wts.repository.SpittleRepository;
import com.wts.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by wtswindows7 on 2017/2/6.
 */

@Service
public class SpittleServiceImpl implements SpittleService {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleServiceImpl(SpittleRepository spittleRepository, SpittleNoticeRepository spittleNoticeRepository) {
        this.spittleRepository = spittleRepository;
    }

    public SpittleRepository getSpittleRepository() {
        return spittleRepository;
    }

    public void setSpittleRepository(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @Override
    public List<Spittle> readMySpittles(User user) {
        return spittleRepository.readSpittleByUser(user);
    }

    @Override
    public void publishSpittle(Spittle spittle) {
        spittleRepository.save(spittle);
    }

    @Override
    public List<Spittle> findFriendSpittles(User user) {
        return spittleRepository.findFriendSpittles(user);
    }
}
