package com.page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by lsx on 16/5/5.
 * 与DragLayout搭配使用
 */
public class ListViewForDrag extends ListView {

    private float mDownY;

    public ListViewForDrag(Context context) {
        super(context);
    }

    public ListViewForDrag(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewForDrag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getRawY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = ev.getRawY() - mDownY;
                //如果到达底部继续往上拉，就让父组件处理此事件
                if (isReachBottomEdge() && dy < 0){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }else if (isReachTopEdge() && dy > 0){
                    //如果到达顶部继续往下拉，就让父组件处理此事件
                    getParent().requestDisallowInterceptTouchEvent(false);
                }else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(ev);
    }

    public boolean isReachTopEdge() {
        boolean result = false;
        if (getFirstVisiblePosition() == 0) {
            final View topChildView = getChildAt(0);
            result = topChildView.getTop() == 0;
        }
        return result;
    }

    public boolean isReachBottomEdge() {
        boolean result = false;
        if (getLastVisiblePosition() == (getCount() - 1)) {
            final View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            result = (getHeight() >= bottomChildView.getBottom());
        }
        return result;
    }
}
