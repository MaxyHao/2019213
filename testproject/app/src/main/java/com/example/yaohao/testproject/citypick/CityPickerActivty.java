package com.example.yaohao.testproject.citypick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.utils.ToastUtils;
import com.example.yaohao.testproject.widget.EnjoyshopToolBar;
import java.util.ArrayList;
import java.util.List;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

public class CityPickerActivty extends AppCompatActivity {

    //IndexableLayout 的适配器
    private CityListAdapter mAdapter;
    //自定义头部adapter
    private BannerHeaderAdapter mBannerHeaderAdapter;
    //热门城市的数组
    private String[] city = {"东莞","深圳","广州","温州","郑州","金华","佛山","上海","苏州","杭州","长沙","中山"};
    private IndexableLayout indexableLayout;
    ////热门城市的适配器
    //private CYBChangeCityGridViewAdapter cybChangeCityGridViewAdapter;
    //热门城市的集合
    private ArrayList<String> list;
    //返回按钮
    private ImageView pic_contact_back;
    private Intent intent;
    private Context mContext;
    @InjectView(R.id.toolbar)
    EnjoyshopToolBar toolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker_activty);
        ButterKnife.inject(this);
        mContext=this;
        initview();
        initAdapter();
        onlisten();
    }

    public void initAdapter(){
        mAdapter = new CityListAdapter(this);
        indexableLayout.setAdapter(mAdapter);
        //设置字母提示框为仿os居中
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());
        //        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED); 设置提示框为仿联系人气泡样式
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        //        indexableLayout.addHeaderAdapter(new SimpleHeaderAdapter<>(mAdapter, "☆",null, null));

        //         构造函数里3个参数,分别对应 (IndexBar的字母索引, IndexTitle, 数据源), 不想显示哪个就传null, 数据源传null时,代表add一个普通的View
        //        mMenuHeaderAdapter = new MenuHeaderAdapter("↑", null, initMenuDatas());
        //        indexableLayout.addHeaderAdapter(mMenuHeaderAdapter);

        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mBannerHeaderAdapter = new BannerHeaderAdapter("↑", null, bannerList);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);
    }

    public void initview(){
        intent = getIntent();
        indexableLayout = (IndexableLayout) findViewById(R.id.indexableLayout);
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onlisten(){

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<CityEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, CityEntity entity) {
                if (originalPosition >= 0) {
                    intent.putExtra("info", entity.getCity());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showShort(mContext, "选中Header/Footer:" + entity.getCity() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    /**
     * 自定义的Banner Header
     */
    class BannerHeaderAdapter extends IndexableHeaderAdapter {
        private static final int TYPE = 1;
        //这里传的参数上面注释有
        public BannerHeaderAdapter(String index, String indexTitle, List datas) {
            super(index, indexTitle, datas);
        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_city_header, parent, false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
            // 数据源为null时, 该方法不用实现
            VH vh = (VH) holder;
            //设置定位城市的点击事件
            vh.item_header_city_dw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //intent.putExtra("bendi", MeNow.city);
                    //setResult(RESULT_OK, intent);
                    //finish();
                }
            });

        }

        private class VH extends RecyclerView.ViewHolder {
            TextView item_header_city_dw;
            public VH(View itemView) {
                super(itemView);
                item_header_city_dw = (TextView) itemView.findViewById(R.id.item_header_city_dw);
            }
        }
    }

    private List<CityEntity> initDatas() {
        List<CityEntity> list = new ArrayList<>();
            list.add(new CityEntity(1,"阿拉善",705));
        list.add(new CityEntity(2,"宝鸡",705));
        list.add(new CityEntity(3,"天水",706));
        list.add(new CityEntity(4,"西安",706));
        list.add(new CityEntity(5,"北京",705));
        list.add(new CityEntity(6,"榆林",706));
        list.add(new CityEntity(7,"贵州",706));
        list.add(new CityEntity(8,"上海",705));
        list.add(new CityEntity(9,"天津",706));
        list.add(new CityEntity(10,"石家庄",706));
        return list;
    }

}
