package com.notbad.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.notbad.lib.common.LogUtils;

public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.d(TAG, "dispatchTouchEvent " + MotionEvent.actionToString(ev.getAction()));
        if(ev.getAction()==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down");
        } else if(ev.getAction()==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move");
        } else if(ev.getAction()==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up");
        }
        boolean result = super.dispatchTouchEvent(ev);
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
