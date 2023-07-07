package com.notbad.thirdpart;

import com.notbad.lib.common.LogUtils;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class RetrofitUtils {
    private static final String TAG = "RetrofitUtils";
    private static String GET_URL =
            "https://raw.githubusercontent.com/Nicolls/sources/main/samples/okhttp/hello.json";
    private static String POST_URL = "https://demo-api.apipost.cn/api/demo/login";

    /**
     * 数据实体
     */
    public class Hello implements Serializable {
        public String name;
        public int age;
        public String country;
        public List<String> language;
        public Child child;
        public Company company;
        public String logo;

        public class Child implements Serializable {
            public String name;
            public int age;
            public String school;
        }

        public class Company implements Serializable {
            public String companyName;
            public List<String> tech;
            public int workers;
        }
    }

    public interface MyService {
        @Headers({ // 多个请求头
                "abc: yes",
                "bbc: no"
        })
        @GET("Nicolls/sources/main/samples/okhttp/{fileName}.json")
        Call<Hello> getHello(@Path("fileName") String fileName, @Query("myQ") String q, @Header("abc") String abc, @Header("add") String add);

        @GET
        Call<Hello> getHello2(@Url String url,  @Query("myQ") String q);
    }

    public void sendRequest() throws IOException {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService service = retrofit.create(MyService.class);
        Call<Hello> hello = service.getHello("hello", "mjk",null,"add");
        LogUtils.d(TAG, "start request");
        hello.enqueue(new Callback<Hello>() {
            @Override
            public void onResponse(Call<Hello> call, Response<Hello> response) {
                LogUtils.d(TAG, "onResponse " + response.body().child.name);
            }

            @Override
            public void onFailure(Call<Hello> call, Throwable t) {
                LogUtils.d(TAG, "onFailure " + t.getMessage());
            }
        });
        LogUtils.d(TAG, "end request");
    }

}
