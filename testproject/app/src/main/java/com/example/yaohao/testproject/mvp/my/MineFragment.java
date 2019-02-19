package com.example.yaohao.testproject.mvp.my;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.mvp.base.BasePresenter;
import com.example.yaohao.testproject.mvp.base.MvpFragment;
import com.example.yaohao.testproject.widget.EnjoyshopToolBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yaohao on 2019/2/14.
 */

public class MineFragment extends MvpFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @InjectView(R.id.mine_smartrefresh)
    SmartRefreshLayout mine_smartrefresh;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,null);
        ButterKnife.inject(this,view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        mine_smartrefresh.setEnableLoadMore(false);
        mine_smartrefresh.setEnableRefresh(false);
        mine_smartrefresh.setEnableOverScrollDrag(true);
        return view;
    }
}
