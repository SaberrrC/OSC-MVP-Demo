package com.zqx.osc_mvp_demo.bussiness;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zqx.osc_mvp_demo.global.MyApp;
import com.zqx.osc_mvp_demo.util.MD5Util;
import com.zqx.osc_mvp_demo.util.StreamUtil;

import java.io.File;

/**
 * Created by ZhangQixiang on 2017/2/16.
 */
public class CacheManager {

    private static File sCacheDir;

    static {

        sCacheDir = new File(
                Environment.getExternalStorageDirectory(),
                MyApp.context.getPackageName() + File.separator + "cache"
        );

        if (!sCacheDir.exists()) {
            boolean mkdirsSuccess = sCacheDir.mkdirs();
            Log.d("缓存目录建立成功?: ",mkdirsSuccess+"");
        }

    }

    /**
     * 去缓存文件中读取数据,如果没有该url对应的缓存数据,返回null
     */
    @Nullable
    public static String getCacheString(String url) {
        File cacheFile = new File(sCacheDir, MD5Util.getMd5(url));
        return StreamUtil.readFrom(cacheFile);
    }

    public static void saveCache(String url, String data) {
        File cacheFile = new File(sCacheDir, MD5Util.getMd5(url));
        StreamUtil.saveString(data, cacheFile);
    }

}
