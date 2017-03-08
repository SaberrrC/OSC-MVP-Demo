package com.zqx.osc_mvp_demo.bussiness;

import android.support.annotation.Nullable;

import com.zqx.osc_mvp_demo.util.GsonUtil;

import java.util.List;

import static com.zqx.osc_mvp_demo.bussiness.DataManager.getDataFromNetOrCache;

/**
 * Created by ZhangQixiang on 2017/2/16.
 */
public class JsonDataManager {

    /**
     * 从网络或缓存中获取json数据对应的Javabean,
     * 若网络和缓存里皆没有数据,则返回null.<br/>
     * Manager中的该方法是同步的,请自行开启子线程
     */
    @Nullable
    public static <T> T getDataBean(String url, Class<T> clazz) {
        String data = getDataFromNetOrCache(url);
        if (data == null) {
            return null;
        }
        return GsonUtil.toBean(data, clazz);

    }

    /**
     * 从网络或缓存中获取json数据对应的List,
     * 若网络和缓存里皆没有数据,则返回null.<br/>
     * Manager中的该方法是同步的,请自行开启子线程<br/>
     */
    @Nullable
    public static <T> List<T> getDataList(String url, Class<T> clazz) {
        String data = getDataFromNetOrCache(url);
        if (data == null) {
            return null;
        }
        return GsonUtil.toList(data, clazz);
    }



}
