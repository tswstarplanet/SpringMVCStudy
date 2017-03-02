package com.wts.service;

import com.wts.domain.Friend;
import com.wts.domain.Spittle;
import com.wts.domain.SpittleNotice;
import com.wts.domain.User;
import com.wts.repository.SpittleNoticeRepository;
import com.wts.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by weitaosheng on 2017/3/2.
 */

@Service
public class SpittleNoticeServiceImpl implements SpittleNoticeService {

    private SpittleNoticeRepository spittleNoticeRepository;

    @Autowired
    public SpittleNoticeServiceImpl(SpittleNoticeRepository spittleNoticeRepository) {
        this.spittleNoticeRepository = spittleNoticeRepository;
    }

    @Override
    public void noticeFriend(User user, Spittle spittle) {
        List<SpittleNotice> spittleNotices = new ArrayList<>();
        Set<Friend> friends = user.getFriends();
        for (Friend friend : friends) {
            if (Constants.FRIEND_RELATIONSHIP_APPROVED == friend.getStatus()) {
                SpittleNotice spittleNotice = new SpittleNotice(user.getUserid(), friend.getFriend().getUserid(), spittle.getId());
                spittleNotices.add(spittleNotice);
            }
        }
        Set<Friend> companies = user.getCompanies();
        for (Friend company : companies) {
            if (Constants.FRIEND_RELATIONSHIP_APPROVED == company.getStatus()) {
                SpittleNotice spittleNotice = new SpittleNotice(user.getUserid(), company.getUser().getUserid(), spittle.getId());
                spittleNotices.add(spittleNotice);
            }
        }
        spittleNoticeRepository.save(spittleNotices);
    }

}
