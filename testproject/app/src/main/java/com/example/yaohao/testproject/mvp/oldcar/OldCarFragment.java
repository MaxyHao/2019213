package com.example.yaohao.testproject.mvp.oldcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.adapter.MoRenPaixuAdapter;
import com.example.yaohao.testproject.bean.MorenPaiXuEntity;
import com.example.yaohao.testproject.bean.ShouFuEntity;
import com.example.yaohao.testproject.bean.YueGongEntity;
import com.example.yaohao.testproject.mvp.base.MvpFragment;
import com.example.yaohao.testproject.utils.LocalDataUtils;
import com.example.yaohao.testproject.widget.EnjoyshopToolBar;
import com.example.yaohao.testproject.widget.ScrollViewTopXuanFuForListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yaohao on 2019/2/14.
 */

public class OldCarFragment extends MvpFragment<OldCarPresenter> implements View.OnClickListener, OldCarListView, ScrollViewTopXuanFuForListView.OnScrollToBottomListener {
    private LinearLayout mPopView;
    private ArrayList<MorenPaiXuEntity> mMoRenPaiXuList;
    private ArrayList<ShouFuEntity> mShouFuList;
    private ArrayList<YueGongEntity> mYueGongList;
    private PopupWindow mMorenPopupWindow;
    private boolean isvisiable;
    private ListView mMorenPaixuListview;
    private MoRenPaixuAdapter moRenPaixuAdapter;


    @InjectView(R.id.toolbar)
    EnjoyshopToolBar toolBar;
    @InjectView(R.id.oldcar_listview)
    RecyclerView oldcar_listview;
    @InjectView(R.id.oldcar_Refresh)
    SmartRefreshLayout oldcar_Refresh;

    //顶部条件选择栏
    @InjectView(R.id.carlist_move_option_layout)
    LinearLayout carlist_move_option_layout;

    @InjectView(R.id.cb_morenpaixu)
    CheckBox cb_MoRenPaiXu;
    @InjectView(R.id.cb_shoufu)
    CheckBox cb_ShouFu;
    @InjectView(R.id.cb_yuegong)
    CheckBox cb_YueGong;
    @InjectView(R.id.cb_pinpai)
    CheckBox cb_PinPai;
    @InjectView(R.id.cb_shaixuan)
    CheckBox cb_ShaiXuan;
    @InjectView(R.id.scrollView)
    ScrollViewTopXuanFuForListView scrollView;
    //悬浮顶部容器
    @InjectView(R.id.carlist_scrollView_viewgroup)
    FrameLayout carlist_scrollView_viewgroup;
    @InjectView(R.id.carlist_top_viewgroup)
    FrameLayout carlist_top_viewgroup;
    private PopupWindow mShouFuPopupWindow;
    private LinearLayout mshoufuPopView;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oldcar, null);
        ButterKnife.inject(this, view);
        initdata();
        scrollView.setOnScrollToBottomLintener(this);
        return view;
    }

    @Override
    protected OldCarPresenter createPresenter() {
        return new OldCarPresenter(this);
    }

    private void initdata() {
        mMoRenPaiXuList = LocalDataUtils.getMoRenPaiXuList();
        mShouFuList = LocalDataUtils.getShouFuList();
        mYueGongList = LocalDataUtils.getYueGongList();
        carlist_move_option_layout.bringToFront();
    }

    @Override
    @OnClick({R.id.cb_shaixuan, R.id.cb_pinpai, R.id.cb_yuegong, R.id.cb_shoufu, R.id.cb_morenpaixu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_morenpaixu:
                int top = carlist_scrollView_viewgroup.getTop();
                scrollView.smoothScrollTo(0, top);
                if (cb_MoRenPaiXu.isChecked()) {
                    showMoRenPaiXu(mMoRenPaiXuList);
                } else {
                    mMorenPopupWindow.dismiss();
                }
                break;
            case R.id.cb_pinpai:

                break;
            case R.id.cb_yuegong:

                break;
            case R.id.cb_shoufu:
                int top1 = carlist_scrollView_viewgroup.getTop();
                scrollView.smoothScrollTo(0, top1);
                if (cb_ShouFu.isChecked()) {
                    showShouFu(mShouFuList);
                } else {
                    mShouFuPopupWindow.dismiss();
                }
                break;
            case R.id.cb_shaixuan:

                break;
        }
    }

    public void showMoRenPaiXu(final ArrayList<MorenPaiXuEntity> morenpaixu) {
        // 将布局文件转换成View对象，popupview 内容视图
        mPopView = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_morenpaixu, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        mMorenPopupWindow = new PopupWindow(mPopView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        mMorenPaixuListview = mPopView.findViewById(R.id.morenpaixu_listview);
        moRenPaixuAdapter = new MoRenPaixuAdapter(getActivity(), morenpaixu);
        mMorenPaixuListview.setAdapter(moRenPaixuAdapter);
        mMorenPopupWindow.setOutsideTouchable(true);
        mMorenPopupWindow.setFocusable(true);
//        mMorenPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mMorenPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white_color_disable));
        mMorenPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cb_MoRenPaiXu.setChecked(false);
            }
        });
        mMorenPaixuListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MorenPaiXuEntity entity = moRenPaixuAdapter.getItem(position);
                entity.setCheck(true);
                for (MorenPaiXuEntity entity1 : morenpaixu) {
                    if (entity.getId() == entity1.getId()) {
                        entity1.setCheck(true);
                    } else {
                        entity1.setCheck(false);
                    }
                }
                moRenPaixuAdapter.notifyDataSetChanged();
            }
        });
        mMorenPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mMorenPopupWindow.showAsDropDown(cb_MoRenPaiXu, 0, 0, Gravity.BOTTOM);

    }

    public void showShouFu(ArrayList<ShouFuEntity> shoufulist) {
        // 将布局文件转换成View对象，popupview 内容视图
        mshoufuPopView = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_shoufu, null);
        // 将转换的View放置到 新建一个popuwindow对象中
        mShouFuPopupWindow = new PopupWindow(mshoufuPopView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 点击popuwindow外让其消失
        CheckBox SF_CB_buxian = mshoufuPopView.findViewById(R.id.SF_CB_buxian);
        CheckBox SF_CB_1WNei = mshoufuPopView.findViewById(R.id.SF_CB_1WNei);
        CheckBox SF_CB_1_2W = mshoufuPopView.findViewById(R.id.SF_CB_1_2W);
        CheckBox SF_CB_2_3W = mshoufuPopView.findViewById(R.id.SF_CB_2_3W);
        CheckBox SF_CB_3_4W = mshoufuPopView.findViewById(R.id.SF_CB_3_4W);
        CheckBox SF_CB_4_5W = mshoufuPopView.findViewById(R.id.SF_CB_4_5W);
        CheckBox SF_CB_5WShang = mshoufuPopView.findViewById(R.id.SF_CB_5WShang);
        SF_CB_buxian.setText(shoufulist.get(0).getTitle());
        SF_CB_1WNei.setText(shoufulist.get(1).getTitle());
        SF_CB_1_2W.setText(shoufulist.get(2).getTitle());
        SF_CB_2_3W.setText(shoufulist.get(3).getTitle());
        SF_CB_3_4W.setText(shoufulist.get(4).getTitle());
        SF_CB_4_5W.setText(shoufulist.get(5).getTitle());
        SF_CB_5WShang.setText(shoufulist.get(6).getTitle());

        for (ShouFuEntity entity : shoufulist) {
            switch (entity.getId()) {
                case 1:
                    if (entity.isCheck()){
                        SF_CB_buxian.setChecked(true);
                    }else {
                        SF_CB_buxian.setChecked(false);
                    }
                    break;
                case 2:
                    if (entity.isCheck()){
                        SF_CB_1WNei.setChecked(true);
                    }else {
                        SF_CB_1WNei.setChecked(false);
                    }
                    break;
                case 3:
                    if (entity.isCheck()){
                        SF_CB_1_2W.setChecked(true);
                    }else {
                        SF_CB_1_2W.setChecked(false);
                    }
                    break;
                case 4:
                    if (entity.isCheck()){
                        SF_CB_2_3W.setChecked(true);
                    }else {
                        SF_CB_2_3W.setChecked(false);
                    }
                    break;
                case 5:
                    if (entity.isCheck()){
                        SF_CB_3_4W.setChecked(true);
                    }else {
                        SF_CB_3_4W.setChecked(false);
                    }
                    break;
                case 6:
                    if (entity.isCheck()){
                        SF_CB_4_5W.setChecked(true);
                    }else {
                        SF_CB_4_5W.setChecked(false);
                    }
                    break;
                case 7:
                    if (entity.isCheck()){
                        SF_CB_5WShang.setChecked(true);
                    }else {
                        SF_CB_5WShang.setChecked(false);
                    }
                    break;
            }
        }
        mShouFuPopupWindow.setOutsideTouchable(true);
        mShouFuPopupWindow.setFocusable(true);
        mShouFuPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white_color_disable));
        mShouFuPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                cb_ShouFu.setChecked(false);
            }
        });
        mShouFuPopupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        mShouFuPopupWindow.showAsDropDown(cb_ShouFu, 0, 0, Gravity.BOTTOM);
    }

    @Override
    public void onScrollViewChangeListener(int l, int t, int oldl, int oldt) {
        if (t > carlist_scrollView_viewgroup.getTop() && !isvisiable) {
            isvisiable = true;
            carlist_scrollView_viewgroup.removeView(carlist_move_option_layout);
            carlist_top_viewgroup.addView(carlist_move_option_layout);
        } else if (t < carlist_scrollView_viewgroup.getTop() + carlist_move_option_layout.getHeight() && isvisiable) {
            isvisiable = false;
            carlist_top_viewgroup.removeAllViews();
            carlist_scrollView_viewgroup.addView(carlist_move_option_layout);
        }
    }

    //查找数据成功
    @Override
    public void onFindCarListSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onFinish() {

    }
}
