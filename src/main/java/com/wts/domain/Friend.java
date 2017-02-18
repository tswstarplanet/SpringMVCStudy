package com.wts.domain;

import javax.persistence.*;

/**
 * Created by weitaosheng on 2017/2/18.
 */

@Entity
@Table(name = "friend")
public class Friend {

    private long id;
    private User user;
    private User friend;

    private int status;
    private long actionId;

    public Friend() {

    }

    public Friend(User user, User friend, int status, long actionId) {
        this.user = user;
        this.friend = friend;
        this.status = status;
        this.actionId = actionId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "friendid", referencedColumnName = "userid")
    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }
}
