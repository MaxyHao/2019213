package com.example.yaohao.testproject.mvp.pinpailist;

import java.io.Serializable;

/**
 * Created by yaohao on 2019/2/20.
 */

public class PinPaiEntity implements Serializable {
    private int id;
    private String title;
    private String iconUrl;
    private int  icon;
    private String sortLetters;

    public PinPaiEntity(int id, String title, String iconUrl) {
        this.id = id;
        this.title = title;
        this.iconUrl = iconUrl;
    }

    public PinPaiEntity(int id, String title, int  icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getIconUrl() {
        return iconUrl;
    }

}
