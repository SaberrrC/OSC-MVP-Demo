package com.zqx.osc_mvp_demo.ui.view;

/**
 * Created by ZhangQixiang on 2017/3/8.
 */

public interface NetDataView<T> {
    void onDataSuccess(int requestCode, T dataBean);

    void onDataFailed(int requestCode);
}
