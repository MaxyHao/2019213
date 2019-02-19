package com.example.yaohao.testproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yaohao.testproject.R;
import com.example.yaohao.testproject.bean.MorenPaiXuEntity;

import java.util.ArrayList;

/**
 * Created by yaohao on 2019/2/15.
 */

public class MoRenPaixuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MorenPaiXuEntity>  mdata;


    public MoRenPaixuAdapter(Context context, ArrayList mdata) {
        this.context = context;
        if (mdata==null){
            this.mdata = new ArrayList<MorenPaiXuEntity>();
        }else {
            this.mdata = mdata;
        }
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public MorenPaiXuEntity getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_morenpaixu_item,null);
            holder.title=convertView.findViewById(R.id.morenpaixu_title);
            holder.checkBox=convertView.findViewById(R.id.morenpaixu_checkbox);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        MorenPaiXuEntity entity=mdata.get(position);
        holder.title.setText(entity.getTitle());
        if (entity.isCheck()){
            holder.title.setTextColor(context.getResources().getColor(R.color.orange));
            holder.checkBox.setChecked(true);
        }else {
            holder.title.setTextColor(context.getResources().getColor(R.color.lightblack));
            holder.checkBox.setChecked(false);
        }
        return convertView;
    }

    class ViewHolder{
        public TextView title;
        public CheckBox checkBox;

    }
}
