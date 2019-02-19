package com.example.yaohao.testproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.yaohao.testproject.base.BaseActivity;
import com.example.yaohao.testproject.bean.MorenPaiXuEntity;
import com.example.yaohao.testproject.bean.Tab;
import com.example.yaohao.testproject.mvp.my.MineFragment;
import com.example.yaohao.testproject.mvp.oldcar.OldCarFragment;
import com.example.yaohao.testproject.mvp.shop.ShopFragment;
import com.example.yaohao.testproject.retrofit.RxBus;
import com.example.yaohao.testproject.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MainActivity extends BaseActivity {
    private FragmentTabHost mTabhost;
    private List<Tab> mTabs = new ArrayList<>();
    private LayoutInflater mInflater;
    @InjectView(R.id.v_bottom)
    View mVBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initTab();
        setNavigationBar(mVBottom);
//        RxBus.get().toFlowable(MorenPaiXuEntity.class).map(new Function<Object, MorenPaiXuEntity>() {
//            @Override
//            public MorenPaiXuEntity apply(@NonNull Object o) throws Exception {
//                return (MorenPaiXuEntity)o;
//            }
//        }).subscribe(new Consumer<MorenPaiXuEntity>() {
//            @Override
//            public void accept(MorenPaiXuEntity morenPaiXuEntity) throws Exception {
//                if (morenPaiXuEntity != null) {
//                }
//            }
//        });
    }

    private void initTab() {
        Tab tab_oldcar = new Tab(OldCarFragment.class, R.string.ershouche, R.drawable.selector_icon_shop);
        Tab tab_shop = new Tab(ShopFragment.class, R.string.shop, R.drawable.selector_icon_shop);
        Tab tab_mine = new Tab(MineFragment.class, R.string.mine, R.drawable.selector_icon_mine);
        mTabs.add(tab_oldcar);
        mTabs.add(tab_shop);
        mTabs.add(tab_mine);
        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);           //默认选中第0个
    }

    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setImageResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
