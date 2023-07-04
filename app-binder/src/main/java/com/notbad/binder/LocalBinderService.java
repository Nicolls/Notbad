package com.notbad.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.notbad.lib.common.LogUtils;

/**
 * 默认情况下，服务与服务声明所在的应用运行于同一进程，并且运行于该应用的主线程中，除非定义了:process。
 * 这是一个本地自己用的Service
 */
public class LocalBinderService extends Service {
    private static final String TAG = "LocalBinderService";

    /**
     * 第一种，实现自己的binder，这个是不能跨进程的。
     * 如果你是使用bindService在起的service，那么，你不能用stopService来停止它，你只能用unbind来解绑，直到没有人绑后，它就自己stop了
     */
    public class MyBinder extends Binder {
        public LocalBinderService getService(){
            return LocalBinderService.this;
        }
    }

    public int calculate(int a,int b){
        return a+b;
    }

    // 这个方法和startService是相对的，如果是bindService才走这个方法，如果是startService走的是oonStartCommand方法
    // 然而如果是bind启动的service，且只有一个的话，它的生命周期跟绑定组件一样，但如果你希望你的service既可以独立支行不依赖bind
    // 又得提供bind，那么，你应该start这个service，然后让别人来bind。
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBind " +  intent);
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
    }

    // 这个方法和onBind是相对的，如果是startService才走这个方法，如果是bindService走的是onBind方法

    /**
     *
     请注意，onStartCommand() 方法必须返回整型数。整型数是一个值，用于描述系统应如何在系统终止服务的情况下继续运行服务。IntentService 的默认实现会为您处理此情况，但您可以对其进行修改。从 onStartCommand() 返回的值必须是以下常量之一：

     START_NOT_STICKY
     如果系统在 onStartCommand() 返回后终止服务，则除非有待传递的挂起 Intent，否则系统不会重建服务。这是最安全的选项，可以避免在不必要时以及应用能够轻松重启所有未完成的作业时运行服务。
     START_STICKY
     如果系统在 onStartCommand() 返回后终止服务，则其会重建服务并调用 onStartCommand()，但不会重新传递最后一个 Intent。相反，除非有挂起 Intent 要启动服务，否则系统会调用包含空 Intent 的 onStartCommand()。在此情况下，系统会传递这些 Intent。此常量适用于不执行命令、但无限期运行并等待作业的媒体播放器（或类似服务）。
     START_REDELIVER_INTENT
     如果系统在 onStartCommand() 返回后终止服务，则其会重建服务，并通过传递给服务的最后一个 Intent 调用 onStartCommand()。所有挂起 Intent 均依次传递。此常量适用于主动执行应立即恢复的作业（例如下载文件）的服务。
     *
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommand " + intent);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        LogUtils.d(TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }



}