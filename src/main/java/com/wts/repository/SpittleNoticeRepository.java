package com.wts.repository;

import com.wts.domain.SpittleNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by weitaosheng on 2017/3/2.
 */

@Repository
public interface SpittleNoticeRepository extends JpaRepository<SpittleNotice, Long>{

    List<SpittleNoticeRepository> save(List<SpittleNoticeRepository> spittleNoticeRepositories);

}
