package com.zqx.osc_mvp_demo.util;

import android.support.annotation.Nullable;

import com.zqx.osc_mvp_demo.bussiness.ApiService;
import com.zqx.osc_mvp_demo.global.Urls;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZhangQixiang on 2017/1/8.
 */
public class HttpUtil {

    private static Retrofit     sRetrofit;
    private static ApiService   sApiService;
    private static OkHttpClient sOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient
                    .Builder()
                    .connectTimeout(3000, TimeUnit.MILLISECONDS)//连接超时设置为3000ms
                    .readTimeout(3000, TimeUnit.MILLISECONDS)
                    .writeTimeout(3000, TimeUnit.MILLISECONDS)
                    .build();
        }

        return sOkHttpClient;
    }

    private static Retrofit getRetrofit() {
        getOkHttpClient();
        if (sRetrofit == null) {
            sRetrofit = new Retrofit
                    .Builder()
                    .baseUrl(Urls.BASE)
                    .client(sOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

    public static ApiService getApiService() {
        if (sApiService == null) {
            sApiService = getRetrofit().create(ApiService.class);
        }
        return sApiService;
    }


    /**
     * 获取url地址的OkHttp3的Response,发生异常则返回null.<br/>
     * 注:这是同步请求方式得到response
     */
    @Nullable
    public static Response getResponse(String url) {
        try {
            return newOkHttpCall(url).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Call newOkHttpCall(String url) {
        getOkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return sOkHttpClient.newCall(request);
    }

    /**
     * 获取url地址的InputStream,发生异常则返回null
     */
    @Nullable
    public static InputStream getInput(String url) {
        Response response = getResponse(url);
        if (response != null) {
            return response.body().byteStream();
        } else {
            return null;
        }
    }

    /**
     * 获取url地址的string数据,发生异常则返回null
     */
    @Nullable
    public static String getString(String url) {
        try {
            return getResponse(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * OkHttp Post请求
     */
    public static Poster Form() {
        return new Poster();
    }

    public static class Poster {
        private FormBody.Builder mFormBuilder;

        public Poster() {
            mFormBuilder = new FormBody.Builder();
        }

        public Poster add(String key, String value) {
            mFormBuilder.add(key, value);
            return this;
        }

        public String postTo(String url) {
            FormBody body = mFormBuilder.build();
            Request request = new Request.Builder().url(url).post(body).build();
            try {
                Response response = getOkHttpClient().newCall(request).execute();
                if (response != null && response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            return null;
        }

    }


}
