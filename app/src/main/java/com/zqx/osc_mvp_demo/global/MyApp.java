package com.zqx.osc_mvp_demo.global;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZhangQixiang on 2017/2/6.
 */
public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
