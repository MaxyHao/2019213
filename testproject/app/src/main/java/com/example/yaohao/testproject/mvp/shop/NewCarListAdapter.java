package com.example.yaohao.testproject.mvp.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yaohao.testproject.R;

import java.util.ArrayList;

/**
 * Created by yaohao on 2019/2/22.
 */

public class NewCarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private ArrayList<NewCarEntity> mData = new ArrayList<NewCarEntity>();
    private Context mContext;
    private LayoutInflater inflater;

    public NewCarListAdapter(ArrayList<NewCarEntity> mData, Context mContext, LayoutInflater inflater) {
        if (mData != null) {
            this.mData = mData;
        }
        this.mContext = mContext;
        this.inflater = inflater;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//       if (viewType ==1) {
        View view = inflater.inflate(R.layout.layout_newcar_list_item, parent, false);
        NewCarHolder newCarHolder = new NewCarHolder(view);
        view.setOnClickListener(newCarHolder);
        return newCarHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewCarEntity newsEntity = (NewCarEntity) mData.get(position);
        if (holder instanceof NewCarHolder) {
            NewCarHolder oneHolder = (NewCarHolder) holder;
            if (mData != null) {

            }
            ((NewCarHolder) oneHolder).itemView.setTag(position);
        }
    }

    public void setDatas(ArrayList<NewCarEntity> list) {
        if (list!=null){
            mData.clear();
            mData.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void appendData(ArrayList<NewCarEntity> data) {
        if (data != null && data.size() > 0) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }
    //定义recycleview的点击事件
    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        int type = 1;
//        if (mData.get(position) instanceof NewCarEntity){
//            type = 3;
//        }
//        return type;
//    }



    public class NewCarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        private ImageView vertical_showimage_img;
//        private BiaoHongTextView vertical_img_title;
//        private TextView vertical_from_where;
//        private TextView vertical_commen_news;
//        private TextView vertical_from_look;
//        private ImageView news_colse;

        public NewCarHolder(View itemView) {
            super(itemView);
//            vertical_showimage_img = MobileUtil.getView(itemView,
//                    R.id.vertical_showimage_img);
//            vertical_img_title = MobileUtil.getView(itemView, R.id.vertical_img_title);
//            vertical_from_where = MobileUtil.getView(itemView, R.id.vertical_from_where);
//            vertical_commen_news = MobileUtil.getView(itemView, R.id.vertical_commen_news);
//            vertical_from_look = MobileUtil.getView(itemView, R.id.vertical_from_look);
//            news_colse = MobileUtil.getView(itemView, R.id.news_colse);
        }


        @Override public void onClick(View v) {
            if(mOnItemClickListener != null){
                //注意这里使用getTag方法获取position
                mOnItemClickListener.onItemClick(v,(int)v.getTag());
            }
        }
    }
}
