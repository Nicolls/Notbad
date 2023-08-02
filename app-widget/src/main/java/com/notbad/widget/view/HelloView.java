package com.notbad.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.notbad.lib.common.LogUtils;

public class HelloView extends View {
    private static final String TAG = "HelloView";

    public HelloView(Context context) {
        super(context);
    }

    public HelloView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HelloView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HelloView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.d(TAG,"onMeasure width:" + getMeasuredWidth()+"--height:" + getMeasuredHeight());
        LogUtils.d(TAG,"onMeasure w:" + getWidth()+"--h:" + getHeight());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.d(TAG,"onLayout width:" + getMeasuredWidth()+"--height:" + getMeasuredHeight());
        LogUtils.d(TAG,"onLayout w:" + getWidth()+"--h:" + getHeight());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.d(TAG, "dispatchTouchEvent " + MotionEvent.actionToString(event.getAction()));
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            LogUtils.d(TAG, "down");
        } else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            LogUtils.d(TAG, "move");
        } else if(event.getAction()==MotionEvent.ACTION_UP) {
            LogUtils.d(TAG, "up");
        }
//        boolean result = false;
        boolean result = super.dispatchTouchEvent(event);

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
