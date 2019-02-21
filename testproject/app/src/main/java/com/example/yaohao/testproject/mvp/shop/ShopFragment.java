package com.example.yaohao.testproject.mvp.shop;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.adapter.GirdDropDownAdapter;
import com.example.yaohao.testproject.adapter.ListShouFuDropDownAdapter;
import com.example.yaohao.testproject.adapter.ListYueGongDropDownAdapter;
import com.example.yaohao.testproject.bean.MorenPaiXuEntity;
import com.example.yaohao.testproject.bean.ScrollViewTopEvent;
import com.example.yaohao.testproject.bean.ShouFuEntity;
import com.example.yaohao.testproject.bean.YueGongEntity;
import com.example.yaohao.testproject.mvp.base.BasePresenter;
import com.example.yaohao.testproject.mvp.base.MvpFragment;
import com.example.yaohao.testproject.retrofit.RxBus;
import com.example.yaohao.testproject.utils.DensityUtils;
import com.example.yaohao.testproject.utils.LocalDataUtils;
import com.example.yaohao.testproject.widget.DropDownMenu;
import com.example.yaohao.testproject.widget.ScrollViewTopXuanFuForListView;

import org.apmem.tools.layouts.FlowLayout;

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

public class ShopFragment extends MvpFragment implements ScrollViewTopXuanFuForListView.OnScrollToBottomListener {

    private static final String TAG_SHOUFU = "2";
    private static final String TAG_YUEGONG = "3";
    @InjectView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    private String headers[] = {"默认排序", "品牌", "首付", "月供"};
    private List<View> popupViews = new ArrayList<>();

    private GirdDropDownAdapter moRenPaixuAdapter;
    private ListShouFuDropDownAdapter shoufuAdapter;
    private ListYueGongDropDownAdapter yuegongAdapter;


    @InjectView(R.id.scrollView_shop)
    ScrollViewTopXuanFuForListView scrollView;

    private boolean isvisiable;
    @InjectView(R.id.carlist_top_viewgroup)
    LinearLayout carlist_top_viewgroup;
    private ArrayList<MorenPaiXuEntity> mMorenpaixu;
    @InjectView(R.id.carlist_move_viewgroup)
    LinearLayout carlist_move_viewgroup;
    private ArrayList<ShouFuEntity> mShoufu;
    private ArrayList<YueGongEntity> mYueGong;
    //tab下条件选择显示容器
    @InjectView(R.id.option_viewGroup)
    FlowLayout mOption_Viewgroup;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        ButterKnife.inject(this, view);
        mContext = getActivity();
        initData();
        initView();
        initRxBus();
        scrollView.setOnScrollToBottomLintener(this);
        mOption_Viewgroup.setVisibility(View.GONE);
        return view;
    }

    private void initData() {
        mMorenpaixu = LocalDataUtils.getMoRenPaiXuList();
        mShoufu = LocalDataUtils.getShouFuList();
        mYueGong = LocalDataUtils.getYueGongList();
    }

    private void initView() {
        //init city menu
        final ListView moRenPaiXuView = new ListView(mContext);
        moRenPaixuAdapter = new GirdDropDownAdapter(mContext, mMorenpaixu);
        moRenPaiXuView.setDividerHeight(0);
        moRenPaiXuView.setDivider(new ColorDrawable(Color.WHITE));
        moRenPaiXuView.setAdapter(moRenPaixuAdapter);
        //init 品牌menu
        final TextView pinpai = new TextView(mContext);
        //init 首付 menu
        final GridView shoufuViews = new GridView(mContext);
        shoufuViews.setNumColumns(3);
        shoufuAdapter = new ListShouFuDropDownAdapter(mContext, mShoufu);
        shoufuViews.setBackgroundResource(R.color.white);
        shoufuViews.setAdapter(shoufuAdapter);
        //init 月供 menu
        final GridView yueGongViews = new GridView(mContext);
        yueGongViews.setNumColumns(3);
        yueGongViews.setBackgroundResource(R.color.white);
        yuegongAdapter = new ListYueGongDropDownAdapter(mContext, mYueGong);
        yueGongViews.setAdapter(yuegongAdapter);
        //init popupViews
        popupViews.add(moRenPaiXuView);
        popupViews.add(pinpai);
        popupViews.add(shoufuViews);
        popupViews.add(yueGongViews);

        //add item click event
        moRenPaiXuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moRenPaixuAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : mMorenpaixu.get(position).getTitle());
                mDropDownMenu.closeMenu();
            }
        });

        shoufuViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shoufuAdapter.setCheckItem(position);
                mOption_Viewgroup.setVisibility(View.VISIBLE);
                addOptionView(mShoufu.get(position).getTitle(), "2");
                mDropDownMenu.closeMenu();
            }
        });

        yueGongViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                yuegongAdapter.setCheckItem(position);
                mOption_Viewgroup.setVisibility(View.VISIBLE);
                addOptionView(mYueGong.get(position).getTitle(), "3");
                mDropDownMenu.closeMenu();
            }
        });

//        constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                constellationAdapter.setCheckItem(position);
//                constellationPosition = position;
//            }
//        });
        //init context view
        TextView textView = new TextView(mContext);
        textView.setText("重置");
        textView.setTextColor(Color.GRAY);
        textView.setBackgroundResource(R.color.transparent);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        LinearLayout.LayoutParams param
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        param.setMargins(10, 10, 10, 10);
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(param);
        final LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.addView(textView);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOption_Viewgroup.removeAllViews();
                shoufuAdapter.setCheckItem(0);
                yuegongAdapter.setCheckItem(0);
                mOption_Viewgroup.addView(linearLayout);
                mOption_Viewgroup.setVisibility(View.GONE);
            }
        });
        mOption_Viewgroup.addView(linearLayout);
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews);

    }

    void addOptionView(String s, String tag) {
        if (mOption_Viewgroup.getChildCount() > 1) {
            for (int i = 0; i < mOption_Viewgroup.getChildCount(); i++) {
                if (mOption_Viewgroup.getChildAt(i).getTag() == tag) {
                    mOption_Viewgroup.removeView(mOption_Viewgroup.getChildAt(i));
                }
                break;
            }
        }

        TextView optionIteam = new TextView(mContext);
        optionIteam.setText(s);
        optionIteam.setGravity(Gravity.CENTER);
        optionIteam.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        optionIteam.setTextColor(getResources().getColor(R.color.orange));
        optionIteam.setBackgroundResource(R.drawable.option_checked);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramss.setMargins(10, 10, 10, 10);
        optionIteam.setPadding(15, 10, 15, 10);
        optionIteam.setLayoutParams(paramss);
        final LinearLayout linearLayouts = new LinearLayout(mContext);
        linearLayouts.addView(optionIteam);
        linearLayouts.setTag(tag);
        linearLayouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOption_Viewgroup.removeView(linearLayouts);
                if (mOption_Viewgroup.getChildCount() == 1) {
                    mOption_Viewgroup.setVisibility(View.GONE);
                }
            }
        });
        if (mOption_Viewgroup.getChildCount() == 1) {
            mOption_Viewgroup.addView(linearLayouts, 0);
        } else {
            mOption_Viewgroup.addView(linearLayouts, 1);
        }

    }

    @Override
    public void onScrollViewChangeListener(int l, int t, int oldl, int oldt) {
        if (t > carlist_move_viewgroup.getTop()) {
            if (!isvisiable && carlist_top_viewgroup.getVisibility() == View.GONE) {
                isvisiable = true;
                carlist_move_viewgroup.removeAllViews();
                carlist_top_viewgroup.setVisibility(View.VISIBLE);
                carlist_top_viewgroup.addView(mDropDownMenu);
            }
        } else if (t < carlist_move_viewgroup.getTop() + mDropDownMenu.getTabView().getHeight()) {
            if (isvisiable && carlist_top_viewgroup.getVisibility() == View.VISIBLE) {
                isvisiable = false;
                carlist_top_viewgroup.removeAllViews();
                carlist_top_viewgroup.setVisibility(View.GONE);
                carlist_move_viewgroup.addView(mDropDownMenu);
            }
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
                        if (event != null && event.istop) {
                            int top = carlist_move_viewgroup.getTop();
                            scrollView.smoothScrollTo(0, top);
                        }
                    }
                });
    }
}
