package com.notbad.lib.algorithm;

public class LogUtils {
    private static final String TAG = "NotBad_";

    public static void d(String msg) {
        d("", msg);
    }

    public static void d(String tag, String msg) {
        System.out.println(TAG + tag + ":" + msg);
    }

    public static void i(String tag, String msg) {
        System.out.println(TAG + tag + ":" + msg);
    }

    public static void w(String tag, String msg) {
        System.out.println(TAG + tag + ":" + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println(TAG + tag + ":" + msg);
    }

    public static void e(String tag, String msg, Exception e) {
        System.out.println(TAG + tag + ":" + msg + " error:" + e.getMessage());
    }
}
