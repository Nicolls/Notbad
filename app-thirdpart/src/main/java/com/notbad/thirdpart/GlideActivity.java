package com.notbad.thirdpart;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.notbad.lib.common.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GlideActivity extends AppCompatActivity {
    private static String TAG = "GlideActivity";
    private static final String IMAGE_URL = "https://www.chnphoto.cn/NewIndex/images/jdyp.jpg";
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_glide);
        imageView = findViewById(R.id.imageview);

    }

    public void onTest(View view) {
        LogUtils.d(TAG, "onTest");
        Glide.with(this).load(IMAGE_URL).into(imageView);

    }
}
