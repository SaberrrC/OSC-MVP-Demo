package com.zqx.osc_mvp_demo.bussiness;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.zqx.osc_mvp_demo.util.HttpUtil;

/**
 * Created by ZhangQixiang on 2017/3/6.
 */

public class DataManager {
    /**
     * 通过OkHttp获取网络数据,若网络请求失败,则从缓存中获取
     */
    @Nullable
    protected static String getDataFromNetOrCache(String url) {
        String data = HttpUtil.getString(url);
        if (TextUtils.isEmpty(data)) {
            data = CacheManager.getCacheString(url);
        } else {
            CacheManager.saveCache(url, data);
        }
        Log.d("data", data);
        return data;

    }
}
