package com.notbad.thirdpart;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.notbad.lib.common.LogUtils;

public class ThirdPartActivity extends AppCompatActivity {
    private static String TAG = "ThirdPartActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

    }

    private RetrofitUtils retrofitUtils = new RetrofitUtils();

    public void onTest(View view) {
        LogUtils.d(TAG, "onTest");
        try {
            retrofitUtils.sendRequest();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
