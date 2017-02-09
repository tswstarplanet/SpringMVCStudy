package com.wts.repository;

import com.wts.domain.Spittle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wtswindows7 on 2017/2/9.
 */

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Long>{
    Spittle save(Spittle spittle);
}
