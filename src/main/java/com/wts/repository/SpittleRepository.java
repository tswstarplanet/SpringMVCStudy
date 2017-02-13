package com.wts.repository;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wtswindows7 on 2017/2/9.
 */

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Long>{
    Spittle save(Spittle spittle);

    List<Spittle> readSpittleByUser(User user);
}
