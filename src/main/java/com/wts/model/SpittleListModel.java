package com.wts.model;

import java.util.List;

/**
 * Created by weitaosheng on 2017/3/4.
 */
public class SpittleListModel {

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private List<SpittleModel> spittleModels;

    public List<SpittleModel> getSpittleModels() {
        return spittleModels;
    }

    public void setSpittleModels(List<SpittleModel> spittleModels) {
        this.spittleModels = spittleModels;
    }
}
