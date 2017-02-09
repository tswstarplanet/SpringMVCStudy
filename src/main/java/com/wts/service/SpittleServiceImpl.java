package com.wts.service;

import com.wts.domain.Spittle;
import com.wts.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wtswindows7 on 2017/2/6.
 */
public class SpittleServiceImpl implements SpittleService {

    @Autowired
    private SpittleRepository spittleRepository;

    public SpittleRepository getSpittleRepository() {
        return spittleRepository;
    }

    public void setSpittleRepository(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    @Override
    public void publishSpittle(Spittle spittle) {
        spittleRepository.save(spittle);
    }
}
