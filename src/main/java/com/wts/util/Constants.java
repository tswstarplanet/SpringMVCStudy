package com.wts.util;

/**
 * Created by weitaosheng on 2017/2/18.
 */
public interface Constants {

    /**
     * 申请好友，申请对象不存在
     */
    public static final int MAKE_FRIEND_APPLY_NOT_EXIST = 1;

    /**
     * 申请好友，发送申请成功
     */
    public static final int MAKE_FRIEND_APPLY_SEND_SUCCESS = 2;

    /**
     * 申请好友，发送申请失败
     */
    public static final int MAKE_FRIEND_APPLY_SEND_FAIL = 3;

    /**
     * 好友状态，申请中
     */
    public static final int FRIEND_RELATIONSHIP_APPLY = 0;

    /**
     * 好友状态，已批准
     */
    int FRIEND_RELATIONSHIP_APPROVED = 4;


    int USER_OFF_LINE = 0;

    int USER_ON_LINE = 1;

}
