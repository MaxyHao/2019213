package com.example.yaohao.testproject.mvp.pinpailist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.yaohao.testproject.R;

import java.util.List;


/**
 * Created by 18481 on 2018/10/29.
 */

public class SortPinPaiAdapter extends BaseAdapter  implements SectionIndexer {
    private List<PinPaiEntity> data = null;
    private Context context;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }
    public SortPinPaiAdapter(Context mContext, List<PinPaiEntity> list) {
        this.context = mContext;
        this.data = list;
    }


    public void updateListView(List<PinPaiEntity> list) {
        this.data = list;
        notifyDataSetChanged();
    }


    public int getCount() {
        return this.data.size();
    }


    public Object getItem(int position) {
        return data.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_pinpai_sort_list_item, null);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.pinpai_title);
            viewHolder.ppIcon = (ImageView) view.findViewById(R.id.pinpai_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }
        final PinPaiEntity entity = data.get(position);

        int section = getSectionForPosition(position);
        viewHolder.tvTitle.setText(entity.getTitle());
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(entity.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        if (entity != null) {
            if (position==0){
                viewHolder.ppIcon.setVisibility(View.GONE);
            }else {
                viewHolder.ppIcon.setVisibility(View.VISIBLE);
            }
            if (checkItemPosition != -1) {
                if (checkItemPosition == position&&position!=0) {
                    viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.orange));
                } else {
                    viewHolder.tvTitle.setTextColor(context.getResources().getColor(R.color.lightblack));
                }
            }

//            view.findViewById(R.id.item_surface)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
        }
        return view;

    }

    class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        ImageView ppIcon;
    }


    public int getSectionForPosition(int position) {
        return data.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = data.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }


    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "*";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
