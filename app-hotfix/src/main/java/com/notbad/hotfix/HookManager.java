package com.notbad.hotfix;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;

import com.notbad.lib.common.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class HookManager {
    private static final String TAG = "HookManager";
    private static volatile HookManager hookManager;

    private HookManager() {
    }

    public static HookManager getInstance() {
        if (hookManager == null) {
            synchronized (HookManager.class) {
                if (hookManager == null) {
                    hookManager = new HookManager();
                }
            }
        }
        return hookManager;
    }

    public static class AmsInvokeHandler implements InvocationHandler {
        private Context context;
        private Object proxyObject;
        public AmsInvokeHandler(Context context, Object proxyObject){
            this.context = context;
            this.proxyObject = proxyObject;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            if("startActivity".equals(method.getName())){
                LogUtils.d(TAG, "startActivityInvoke " + method.getName());
                for (int i = 0;i<args.length;i++){
                    Object arg = args[i];
                    if(arg instanceof Intent){
                        Intent holderIntent = new Intent();
                        holderIntent.setComponent(new ComponentName("com.notbad.hotfix","com.notbad.hotfix.HostRegisterActivity"));
                        holderIntent.putExtra("realIntent",(Intent)arg);
                        args[i] = holderIntent;
                        break;
                    }
                }
            }
            return method.invoke(proxyObject,args);
        }
    }

    public void hookAms(Context context) {
        LogUtils.d(TAG, "hookAms");
        try {
            // 获取 ams对象
            Class<?> activityManagerCls = Class.forName("android.app.ActivityManager");
            Field imsfield = activityManagerCls.getDeclaredField("IActivityManagerSingleton");
            imsfield.setAccessible(true);
            Object IActivityManagerSingleton = imsfield.get(null);
            Class<?> singletonCls = Class.forName("android.util.Singleton");
            Field tfield = singletonCls.getDeclaredField("mInstance");
            tfield.setAccessible(true);
            Object ams = tfield.get(IActivityManagerSingleton);
            LogUtils.d(TAG, "ams :" + ams);
            Class iActivityInterfaceCls = Class.forName("android.app.IActivityManager");
            Object hookAms = Proxy.newProxyInstance(context.getClassLoader(), new Class<?>[]{iActivityInterfaceCls}, new AmsInvokeHandler(context, ams));
            tfield.set(IActivityManagerSingleton, hookAms);

        }catch (Exception e){
            LogUtils.e(TAG, "hook ams error",e);
        }
    }

    public void hookHandler(Context context) {
        LogUtils.d(TAG, "hookHandler");
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field activityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            activityThreadField.setAccessible(true);
            Object activityThread = activityThreadField.get(null);

            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            Object handler = mHField.get(activityThread);
            Field callBackField = Handler.class.getDeclaredField("mCallback");
            callBackField.setAccessible(true);

            Object originalCallBack = callBackField.get(handler);
            callBackField.set(handler,new MyCallBack((Handler.Callback) originalCallBack));

        }catch (Exception e){
            LogUtils.e(TAG, "hook handler error",e);
        }
    }

    private static class MyCallBack implements Handler.Callback {

        private static final int Launch_activity = 100;
        private static final int execute_launch = 159;
        private Handler.Callback originalCallBack;
        public MyCallBack(Handler.Callback originalCallBack){
            this.originalCallBack = originalCallBack;
        }
        @Override
        public boolean handleMessage(Message msg) {
            try {
                switch (msg.what){
                    case Launch_activity:
                        LogUtils.d(TAG, "Launch_activity");
                        Field intentField = msg.obj.getClass().getDeclaredField("intent");
                        intentField.setAccessible(true);
                        Intent proxyIntent = (Intent) intentField.get(msg.obj);
                        Parcelable realIntent = proxyIntent.getParcelableExtra("realIntent");
                        if(realIntent!=null){
                            LogUtils.d(TAG, "replace realIntent " + realIntent);
                            intentField.set(msg.obj,realIntent);
                        }
                        break;
                    case execute_launch:
                        LogUtils.d(TAG, "execute launche");
                        Object clientTrasation  =msg.obj;
                        Class clientTrastionCls = clientTrasation.getClass();
                        Field mActivityCallBack = clientTrastionCls.getDeclaredField("mActivityCallbacks");
                        mActivityCallBack.setAccessible(true);
                        List callBacks = (List) mActivityCallBack.get(clientTrasation);
                        for (Object cb:callBacks) {
                            if("android.app.servertransaction.LaunchActivityItem".equals(cb.getClass().getName())){
                                Field mIntentFile = cb.getClass().getDeclaredField("mIntent");
                                mIntentFile.setAccessible(true);
                                Intent intent = (Intent) mIntentFile.get(cb);
                                Parcelable realIntent2 = intent.getParcelableExtra("realIntent");
                                if(realIntent2!=null){
                                    LogUtils.d(TAG, "replace realIntent " + realIntent2);
                                    mIntentFile.set(cb,realIntent2);
                                }
                            }
                        }

                        break;
                }
            }catch (Exception e){
                LogUtils.e(TAG, "handleMessage error",e);
            }

            return false;
        }
    }

}
