package com.wts.repository;

import com.wts.domain.Spittle;
import com.wts.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wtswindows7 on 2017/2/9.
 */

@Repository
public interface SpittleRepository extends JpaRepository<Spittle, Long>{
    Spittle save(Spittle spittle);

    List<Spittle> readSpittleByUser(User user);

//    @Query("select s from Spittle s where s.user in (select f.user from Friend f where f.friend = :user and f.status = 4) or (select f.friend from Friend f where f.user = :user and f.status = 4)")
//    @Query("select s from Spittle s where s.user in ((select f.user from Friend f where f.friend = ?1 and f.status = 4) or (select f.friend from Friend f where f.user = :user and f.status = 4))")
    @Query("select s from Spittle s where (s.user in (select f.user from Friend f where f.friend = ?1 and f.status = 4)) or (s.user in (select f.friend from Friend f where f.user = ?1 and f.status = 4))")
    List<Spittle> findFriendSpittles(User user);
}
