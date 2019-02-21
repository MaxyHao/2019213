package com.example.yaohao.testproject.utils;


import android.support.v4.util.SparseArrayCompat;
import android.view.View;


/**
 * Created by mahaifeng on 16/2/4.
 */
public class MobileUtil {

    public static <T extends View> T getView(View convertView, int id) {
        SparseArrayCompat<View> viewContainer = (SparseArrayCompat<View>) convertView.getTag();
        if (viewContainer == null) {
            viewContainer = new SparseArrayCompat<View>();
            convertView.setTag(viewContainer);
        } else {
            viewContainer = (SparseArrayCompat<View>) convertView.getTag();
        }
        View view = viewContainer.get(id);
        if (view == null) {
            view = convertView.findViewById(id);
            viewContainer.put(id, view);
        }
        return (T) view;
    }

}

