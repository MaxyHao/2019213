package com.example.yaohao.testproject.bean;

/**
 * Created by yaohao on 2019/2/15.
 */

public class MorenPaiXuEntity {

    private  int id;
    private String title;
    private boolean check;

    public MorenPaiXuEntity(int id, String title, boolean check) {
        this.id = id;
        this.title = title;
        this.check = check;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
