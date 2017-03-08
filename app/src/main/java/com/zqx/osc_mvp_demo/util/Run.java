package com.zqx.osc_mvp_demo.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.zqx.osc_mvp_demo.global.MyApp;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZhangQixiang on 2017/2/10.
 */
public class Run {

    private static Handler  sHandler  = new Handler(Looper.getMainLooper());//获取主线程的Looper
    private static Executor sExecutor = new ThreadPoolExecutor(
            1, 10, 10, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    Toast.makeText(MyApp.context, "操作太频繁,线程池已经爆炸", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public static void onSub(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public static void onMain(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void onMain(Runnable runnable, long delay) {
        sHandler.postDelayed(runnable, delay);
    }

}
