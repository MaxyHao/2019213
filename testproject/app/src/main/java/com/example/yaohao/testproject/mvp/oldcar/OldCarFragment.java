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

public class OldCarFragment extends MvpFragment<OldCarPresenter> implements  OldCarListView{

    @InjectView(R.id.toolbar)
    EnjoyshopToolBar toolBar;
    @InjectView(R.id.oldcar_Refresh)
    SmartRefreshLayout oldcar_Refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oldcar, null);
        ButterKnife.inject(this, view);
        initdata();
        return view;
    }

    @Override
    protected OldCarPresenter createPresenter() {
        return new OldCarPresenter(this);
    }

    private void initdata() {

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
