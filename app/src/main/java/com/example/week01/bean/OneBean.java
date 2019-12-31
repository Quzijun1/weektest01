package com.example.week01.bean;

import java.io.Serializable;
import java.util.List;

public class OneBean implements Serializable {
    private List<TwoBean> smses;

    public List<TwoBean> getSmses() {
        return smses;
    }

    public void setSmses(List<TwoBean> smses) {
        this.smses = smses;
    }
}
