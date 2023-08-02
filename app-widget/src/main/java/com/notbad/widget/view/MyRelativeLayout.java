package com.notbad.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.notbad.lib.common.LogUtils;

public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "MyRelativeLayout";

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "dispatchTouchEvent " + MotionEvent.actionToString(ev.getAction()));
        boolean result = super.dispatchTouchEvent(ev);
//        boolean result = false;

        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "onInterceptTouchEvent " + MotionEvent.actionToString(ev.getAction()));
        if(ev.getAction()==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down");
        } else if(ev.getAction()==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move");
        } else if(ev.getAction()==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up");
        }
        boolean result = super.onInterceptTouchEvent(ev);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "onTouchEvent " + MotionEvent.actionToString(event.getAction()));
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down");
        } else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move");
        } else if(event.getAction()==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up");
        }
        boolean result = super.onTouchEvent(event);
        return result;
    }

}
