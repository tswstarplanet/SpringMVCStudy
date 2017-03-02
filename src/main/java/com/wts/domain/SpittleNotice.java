package com.wts.domain;

import javax.persistence.*;

/**
 * Created by weitaosheng on 2017/3/2.
 */

@Entity
@Table(name = "spittle_notice")
public class SpittleNotice {

    public SpittleNotice(Long userid, Long friendid, Long spittleid) {
        this.userid = userid;
        this.friendid = friendid;
        this.spittleid = spittleid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "friendid")
    private Long friendid;

    @Column(name = "spittle_id")
    private Long spittleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }

    public Long getSpittleid() {
        return spittleid;
    }

    public void setSpittleid(Long spittleid) {
        this.spittleid = spittleid;
    }
}
