package com.example.yaohao.testproject.citypick;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * Created by Administrator on 2019/2/25.
 */

public class CityEntity implements IndexableEntity {
    private int  id;
    private String  city;
    private int  key;
    private int  provice;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public int getKey() {
        return key;
    }


    public void setKey(int key) {
        this.key = key;
    }


    public int getProvice() {
        return provice;
    }


    public void setProvice(int provice) {
        this.provice = provice;
    }


    @Override
    public String getFieldIndexBy() {
        return city;
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        this.city = indexField;
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {
        // 需要用到拼音时(比如:搜索), 可增添pinyin字段 this.pinyin  = pinyin
        // 见 CityEntity
    }


    public CityEntity(int id, String city, int key) {
        this.id = id;
        this.city = city;
        this.key = key;
    }
}
