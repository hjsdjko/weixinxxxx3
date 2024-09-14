package com.example.vo;

import com.example.entity.GoumaiInfo;

public class GoumaiInfoVo extends GoumaiInfo {

    private String tupian;
    private String mima;

    @Override
    public String getMima() {
        return mima;
    }

    @Override
    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }
}
