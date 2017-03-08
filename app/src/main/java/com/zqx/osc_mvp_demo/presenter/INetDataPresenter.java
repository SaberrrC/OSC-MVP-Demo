package com.zqx.osc_mvp_demo.presenter;

/**
 * Created by ZhangQixiang on 2017/3/3.
 *
 * 所有的逻辑为 [获取网络数据并通知View] 的presenter的接口
 */
public interface INetDataPresenter<T> {
    /**
     * @param requestCode 作为一个附加参数,可标记本次请求是下拉刷新,还是加载更多,还是...
     */
     void getDataAndNoticeView(int requestCode, String url, Class<T> beanClass);

}
