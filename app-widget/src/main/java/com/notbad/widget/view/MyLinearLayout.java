package com.notbad.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.notbad.lib.common.LogUtils;

public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
    public boolean onInterceptTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "onInterceptTouchEvent " + MotionEvent.actionToString(event.getAction()));
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down");
        } else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move");
        } else if(event.getAction()==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up");
        }
        boolean result = super.onInterceptTouchEvent(event);
//        boolean result = super.onInterceptTouchEvent(ev);

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
//        boolean result = true;

        return result;
    }
}
