package com.example.yaohao.testproject.utils;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.bean.MorenPaiXuEntity;
import com.example.yaohao.testproject.bean.ShouFuEntity;
import com.example.yaohao.testproject.bean.YueGongEntity;
import com.example.yaohao.testproject.mvp.pinpailist.PinPaiEntity;

import java.util.ArrayList;

/**
 * Created by yaohao on 2019/2/15.
 */

public class LocalDataUtils {

    //查询首付范围
    public static ArrayList<MorenPaiXuEntity> getMoRenPaiXuList() {
        ArrayList<MorenPaiXuEntity> list = new ArrayList<MorenPaiXuEntity>();
        list.add(new MorenPaiXuEntity(1, "默认排序", true));
        list.add(new MorenPaiXuEntity(2, "车龄最短", false));
        list.add(new MorenPaiXuEntity(3, "里程最少", false));
        list.add(new MorenPaiXuEntity(4, "车价最高", false));
        list.add(new MorenPaiXuEntity(5, "车价最低", false));
        list.add(new MorenPaiXuEntity(6, "首付最低", false));
        list.add(new MorenPaiXuEntity(7, "月供最低", false));
        return list;
    }
    //查询首付范围
    public static ArrayList<ShouFuEntity> getShouFuList() {
        ArrayList<ShouFuEntity> list = new ArrayList<ShouFuEntity>();
        list.add(new ShouFuEntity(1, "不限", true));
        list.add(new ShouFuEntity(2, "1万以内", false));
        list.add(new ShouFuEntity(3, "1-2万", false));
        list.add(new ShouFuEntity(4, "2-3万", false));
        list.add(new ShouFuEntity(5, "3-4万", false));
        list.add(new ShouFuEntity(6, "4-5万", false));
        list.add(new ShouFuEntity(7, "5万以上", false));
        return list;
    }

    //查询月供范围
    public static ArrayList<YueGongEntity> getYueGongList() {
        ArrayList<YueGongEntity> list = new ArrayList<YueGongEntity>();
        list.add(new YueGongEntity(1, "不限", true));
        list.add(new YueGongEntity(2, "2000元以内", false));
        list.add(new YueGongEntity(3, "2000-3000元", false));
        list.add(new YueGongEntity(4, "3000-4000元", false));
        list.add(new YueGongEntity(5, "4000-5000元", false));
        list.add(new YueGongEntity(6, "5000以上", false));
        return list;
    }


    //查询月供范围
    public static ArrayList<PinPaiEntity> getPinPaiList() {
        ArrayList<PinPaiEntity> list = new ArrayList<PinPaiEntity>();
        list.add(new PinPaiEntity(1, "不限品牌", R.mipmap.pp_bmw));
        list.add(new PinPaiEntity(2, "宝马", R.mipmap.pp_bmw));
        list.add(new PinPaiEntity(3, "奔驰", R.mipmap.pp_bmw));
        list.add(new PinPaiEntity(4, "雪佛兰", R.mipmap.pp_bmw));
        list.add(new PinPaiEntity(5, "玛莎拉蒂", R.mipmap.pp_bmw));
        list.add(new PinPaiEntity(6, "奇瑞", R.mipmap.pp_bmw));
        return list;
    }
}
