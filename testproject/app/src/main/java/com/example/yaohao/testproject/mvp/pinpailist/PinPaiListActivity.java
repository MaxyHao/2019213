package com.example.yaohao.testproject.mvp.pinpailist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.mvp.shop.ShopFragment;
import com.example.yaohao.testproject.utils.LocalDataUtils;
import com.example.yaohao.testproject.widget.EnjoyshopToolBar;
import com.example.yaohao.testproject.widget.sideBar.CharacterParser;
import com.example.yaohao.testproject.widget.sideBar.PinyinPinPaiComparator;
import com.example.yaohao.testproject.widget.sideBar.SideBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.ArrayList;
import java.util.Collections;

public class PinPaiListActivity extends AppCompatActivity implements SectionIndexer {


    @InjectView(R.id.pinpailist_refreshLayout)
    SmartRefreshLayout mPinPaiList_RefreshLayout;
    @InjectView(R.id.toolbar)
    EnjoyshopToolBar toolBar;

    private CharacterParser characterParser;
    private PinyinPinPaiComparator pinyinComparator;
    private LinearLayout titleLayout;
    private TextView title;
    private TextView tvNofriends;
    private ListView listPinPai;
    private SideBar sideBar;
    private TextView dialogs;
    private SortPinPaiAdapter sortPinPaiAdapter;
    private ArrayList<PinPaiEntity> mPinPaiList;
    private int lastFirstVisibleItem = -1;
    private Context mContext;
    private int mPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_pai_list);
        ButterKnife.inject(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
        mContext=this;
        mPinPaiList = LocalDataUtils.getPinPaiList();
        mPosition=getIntent().getIntExtra(ShopFragment.TAG_PP_POSITION,0);
        initViews();
        initData();

        mPinPaiList_RefreshLayout.setEnableLoadMore(false);
        mPinPaiList_RefreshLayout.setEnableRefresh(false);
        mPinPaiList_RefreshLayout.setEnableOverScrollDrag(true);
        listPinPai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               PinPaiEntity pinPaiEntity= (PinPaiEntity) sortPinPaiAdapter.getItem(position);
                sortPinPaiAdapter.setCheckItem(position);
                Intent intent=new Intent();
                intent.putExtra(ShopFragment.TAG_SELECTEDPINPAI,pinPaiEntity);
                intent.putExtra(ShopFragment.TAG_PP_POSITION,position);
                setResult(RESULT_OK,intent);
                finish();
                overridePendingTransition(R.anim.oldactivity_start_right,R.anim.activity_end_right);

            }
        });

    }
    //初始化数据
    private void initData() {

        mPinPaiList = filledData(mPinPaiList);
        Collections.sort(mPinPaiList, pinyinComparator);
        if (sortPinPaiAdapter == null) {
            sortPinPaiAdapter = new SortPinPaiAdapter(mContext, mPinPaiList);
            sortPinPaiAdapter.setCheckItem(mPosition);
            listPinPai.setAdapter(sortPinPaiAdapter);
        } else {
            sortPinPaiAdapter.updateListView(mPinPaiList);
        }

        if (mPinPaiList.size() > 0) {
            listPinPai.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {
                    int section = getSectionForPosition(firstVisibleItem);
                    if (firstVisibleItem != lastFirstVisibleItem) {
                        ViewGroup.MarginLayoutParams params
                                = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        params.topMargin = 0;
                        titleLayout.setLayoutParams(params);
                        title.setText(mPinPaiList.get(
                                getPositionForSection(section)).getSortLetters());
                    }
                    if (mPinPaiList.size() > 1) {
                        int nextSection = getSectionForPosition(firstVisibleItem + 1);
                        int nextSecPosition = getPositionForSection(+nextSection);
                        if (nextSecPosition == firstVisibleItem + 1) {
                            View childView = view.getChildAt(0);
                            if (childView != null) {
                                int titleHeight = titleLayout.getHeight();
                                int bottom = childView.getBottom();
                                ViewGroup.MarginLayoutParams params
                                        = (ViewGroup.MarginLayoutParams) titleLayout
                                        .getLayoutParams();
                                if (bottom < titleHeight) {
                                    float pushedDistance = bottom - titleHeight;
                                    params.topMargin = (int) pushedDistance;
                                    titleLayout.setLayoutParams(params);
                                } else {
                                    if (params.topMargin != 0) {
                                        params.topMargin = 0;
                                        titleLayout.setLayoutParams(params);
                                    }
                                }
                            }
                        }
                    }
                    lastFirstVisibleItem = firstVisibleItem;
                }
            });
        }
    }

    //初始化View
    private void initViews() {
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title_layout_catalog);
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinPinPaiComparator();
        listPinPai = (ListView) findViewById(R.id.lv_pinpailist);
        sideBar = (SideBar) findViewById(R.id.sidebar);
        dialogs = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialogs);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = sortPinPaiAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listPinPai.setSelection(position);
                }
            }
        });
    }

    /**
     * ΪListView
     */
    private ArrayList<PinPaiEntity> filledData(ArrayList<PinPaiEntity> date) {
        ArrayList<PinPaiEntity> mSortList = new ArrayList<PinPaiEntity>();

        for (int i = 0; i < date.size(); i++) {
            PinPaiEntity sortModel = date.get(i);
            //转化拼音
            String pinyin = characterParser.getSelling(date.get(i).getTitle());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if ("不限品牌".equals(sortModel.getTitle())) {
                sortModel.setSortLetters("*");
            } else if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            }
        }
        return date;

    }


//    /**
//     * ListView
//     */
//    private void filterData(String filterStr) {
//        List<PinPaiEntity> filterDateList = new ArrayList<PinPaiEntity>();
//
//        if (TextUtils.isEmpty(filterStr)) {
//            filterDateList = mPinPaiList;
//            tvNofriends.setVisibility(View.GONE);
//        } else {
//            filterDateList.clear();
//            for (PinPaiEntity sortModel : mPinPaiList) {
//                String name = sortModel.getTitle();
//                if (name.indexOf(filterStr.toString()) != -1
//                        || characterParser.getSelling(name).startsWith(
//                        filterStr.toString())) {
//                    filterDateList.add(sortModel);
//                }
//            }
//        }

//        // ���a-z��������
//        Collections.sort(filterDateList, pinyinComparator);
//        sortPinPaiAdapter.updateListView(filterDateList);
//        if (filterDateList.size() == 0) {
//            tvNofriends.setVisibility(View.VISIBLE);
//        }
//    }


    @Override
    public Object[] getSections() {
        return null;
    }


    /**
     * Char asciiֵ
     */
    public int getSectionForPosition(int position) {
        int i = 0;
        if (sortPinPaiAdapter != null && mPinPaiList.size() > 0) {
            i = mPinPaiList.get(position).getSortLetters().charAt(0);
        }
        return i;
    }


    public int getPositionForSection(int section) {
        for (int i = 0; i < mPinPaiList.size(); i++) {
            String sortStr = mPinPaiList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.oldactivity_start_right,R.anim.activity_end_right);
    }
}









