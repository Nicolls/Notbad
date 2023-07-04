package com.notbad.binder;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.notbad.lib.common.LogUtils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void startMyService(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        context.startService(intent);
    }

    private static String POST_URL = "https://demo-api.apipost.cn/api/demo/login";
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onHandleIntent(Intent intent) {
        /**
         *
         * Service中调用onHandleIntent后就会调用stopSelf方法，接着Service就会触发destroy
         * 如果我们不停的start这个service，时它仍然会执行完你的每一次start触发的onHandler后才会destroy，
         * 原因是在处理Service stopSelf时，其中ActiveServices会判断，当前的startId是否和lastStartId一样，如果不一样则不走destroy逻辑、
         * 所以我们后面仍能发送service的消息，使用着同一个service来处理数据
         *
         * if (r.getLastStartId() != startId) {
         *                     return false;
         *                 }
         */
        LogUtils.d(TAG, "onHandleIntent");
        RequestBody reqBody = new FormBody.Builder()
                .add("mobile", "18289454846")
                .add("ver_code", "123456").build();
        Request postReq = new Request.Builder().post(reqBody).url(POST_URL).build();
        try {

            Thread.sleep(3000);
            LogUtils.d(TAG, "start to post");
            Response postResp = client.newCall(postReq).execute();
            String postResult = postResp.body().string();
            LogUtils.d(TAG, "post result:" + postResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d(TAG, "onCreate");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        LogUtils.d(TAG, "onStart");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, "onDestroy");
    }

    /**
     * intentService的默认实现是return null，说明intentService是不允许绑定的，也就是不允许跨进行IPC
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtils.d(TAG, "onBind");
        return super.onBind(intent);
    }
}