package com.example.yaohao.testproject.mvp.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.adapter.ConstellationAdapter;
import com.example.yaohao.testproject.adapter.GirdDropDownAdapter;
import com.example.yaohao.testproject.adapter.ListDropDownAdapter;
import com.example.yaohao.testproject.bean.ScrollViewTopEvent;
import com.example.yaohao.testproject.mvp.base.BasePresenter;
import com.example.yaohao.testproject.mvp.base.MvpFragment;
import com.example.yaohao.testproject.retrofit.RxBus;
import com.example.yaohao.testproject.widget.DropDownMenu;
import com.example.yaohao.testproject.widget.ScrollViewTopXuanFuForListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by yaohao on 2019/2/14.
 */

public class ShopFragment extends MvpFragment  implements ScrollViewTopXuanFuForListView.OnScrollToBottomListener{

    @InjectView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"默认排序", "品牌", "首付", "月供","筛选"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;
    @InjectView(R.id.scrollView_shop)
    ScrollViewTopXuanFuForListView scrollView;
    //悬浮顶部容器
//    @InjectView(R.id.carlist_scrollView_viewgroup)
//    FrameLayout carlist_scrollView_viewgroup;
    private boolean isvisiable;
    @InjectView(R.id.carlist_top_viewgroup)
    LinearLayout carlist_top_viewgroup;
    private ListDropDownAdapter sexsAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop,null);
        ButterKnife.inject(this, view);
        mContext=getActivity();
        initView();
        initData();
        initRxBus();
        scrollView.setOnScrollToBottomLintener(this);
        return view;
    }

    private void initData() {
    }

    private void initView() {
        //init city menu
        final ListView cityView = new ListView(mContext);
        cityAdapter = new GirdDropDownAdapter(mContext, Arrays.asList(citys));
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);
        //init age menu
        final ListView ageView = new ListView(mContext);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(mContext, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);
        //init sex menu
        final ListView sexView = new ListView(mContext);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(mContext, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init sex menu
        final ListView sexsView = new ListView(mContext);
        sexsView.setDividerHeight(0);
        sexsAdapter = new ListDropDownAdapter(mContext, Arrays.asList(sexs));
        sexsView.setAdapter(sexsAdapter);
        //init constellation
        final View constellationView = getLayoutInflater().inflate(R.layout.drop_custom_layout, null);
        GridView constellation = ButterKnife.findById(constellationView, R.id.constellation);
        constellationAdapter = new ConstellationAdapter(mContext, Arrays.asList(constellations));
        constellation.setAdapter(constellationAdapter);
        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);
        popupViews.add(sexsView);
        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexsAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[4] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });

        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });

        //init context view
//        TextView contentView = new TextView(getActivity());
//        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        contentView.setText("内容显示区域");
//        contentView.setGravity(Gravity.CENTER);
//        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);
    }


    @Override
    public void onScrollViewChangeListener(int l, int t, int oldl, int oldt) {
        Log.d("ChangeListener", l+"-"+t+"-"+oldl+"-"+oldt+"-"+mDropDownMenu .getTop());
        if (t >mDropDownMenu .getTop()+mDropDownMenu.getUnderLine().getHeight() && !isvisiable) {
            isvisiable = true;
            mDropDownMenu.removeView(mDropDownMenu.getTabView());
            mDropDownMenu.removeView(mDropDownMenu.getUnderLine());
            mDropDownMenu.removeView(mDropDownMenu.getContainerView());
            carlist_top_viewgroup.addView(mDropDownMenu.getTabView(),0);
            carlist_top_viewgroup.addView(mDropDownMenu.getUnderLine(),1);
            carlist_top_viewgroup.addView(mDropDownMenu.getContainerView(),2);
        } else if (t < mDropDownMenu.getTop() + mDropDownMenu.getTabView().getHeight()+mDropDownMenu.getUnderLine().getHeight() && isvisiable) {
            isvisiable = false;
            carlist_top_viewgroup.removeAllViews();
            mDropDownMenu.addView(mDropDownMenu.getTabView(),0);
            mDropDownMenu.addView(mDropDownMenu.getUnderLine(),1);
            mDropDownMenu.addView(mDropDownMenu.getContainerView(),2);
        }
    }


    private void initRxBus() {
        RxBus.get().toFlowable(ScrollViewTopEvent.class)
                .map(new Function<Object, ScrollViewTopEvent>() {
                    @Override
                    public ScrollViewTopEvent apply(@NonNull Object o) throws Exception {
                        return (ScrollViewTopEvent) o;
                    }
                })
                .subscribe(new Consumer<ScrollViewTopEvent>() {
                    @Override
                    public void accept(@NonNull ScrollViewTopEvent event) throws Exception {
                        if (event!=null&&event.istop){
                            int top = mDropDownMenu.getTop();
                            scrollView.smoothScrollTo(0, top);
                        }
                    }
                });
    }
}
