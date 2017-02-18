package com.wts.model;

/**
 * Created by weitaosheng on 2017/2/16.
 */
public class MakeFriendResponse {

    /**
     * 1 不存在
     * 2 发送申请成功
     * 3 发送申请失败
     */
    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
