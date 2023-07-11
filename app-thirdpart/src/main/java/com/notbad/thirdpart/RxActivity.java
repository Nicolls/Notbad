package com.notbad.thirdpart;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.notbad.lib.common.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private static String TAG = "ThirdPartActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rxjava);

    }

    public void onTest(View view) {
        LogUtils.d(TAG, "onTest");
        byCreate();

    }


    private void byCreate() {
        LogUtils.d(TAG, "start");
        // 背压
        // 创建被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                LogUtils.d(TAG, "subscribe " + emitter);
                for (int i = 0; i < 50; i++) {
                    emitter.onNext(""+i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<String> subscriber = new Subscriber<String>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                LogUtils.d(TAG, "onSubscribe " + s);
                this.subscription = s;
                this.subscription.request(1); // 设置观察者可以获取多少个事件
            }

            @Override
            public void onNext(String s) {
                LogUtils.d(TAG, "onNext " + s);
                // 每次都要设置还可以获取多少可事件，其实就是告诉被观察都，我可以接收几个事件。像这个这样写就能不段的从观察都那里取到数据
                // 如果这里不回调给观察都，那么就不再去请求事件了
                this.subscription.request(1);

            }
            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                LogUtils.d(TAG, "onComplete ");
            }


        };
        flowable.subscribeOn(Schedulers.io()) // 设置被观察者在io线程中运行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中运行
                .subscribe(subscriber); // 订阅
        LogUtils.d(TAG, "end");

    }

}
