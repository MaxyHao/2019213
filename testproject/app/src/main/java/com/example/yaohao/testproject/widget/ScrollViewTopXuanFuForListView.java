package com.example.yaohao.testproject.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by mahaifeng on 16/5/5.
 */
public class ScrollViewTopXuanFuForListView extends ScrollView {
    private OnScrollToBottomListener onScrollToBottom;

    public ScrollViewTopXuanFuForListView(Context context) {
        super(context);
    }

    public ScrollViewTopXuanFuForListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewTopXuanFuForListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
                                  boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
//        if (scrollY != 0 && null != onScrollToBottom) {
//            onScrollToBottom.onScrollBottomListener(clampedY);
//        }
    }


    @Override protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        onScrollToBottom.onScrollViewChangeListener(l, t, oldl, oldt);
    }


    public void setOnScrollToBottomLintener(OnScrollToBottomListener listener) {
        onScrollToBottom = listener;
    }

    public interface OnScrollToBottomListener {
//        void onScrollBottomListener(boolean isBottom);

        void onScrollViewChangeListener(int l, int t, int oldl, int oldt);
    }
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return false;
//    }
}
