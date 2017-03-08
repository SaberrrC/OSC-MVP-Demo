package com.zqx.osc_mvp_demo.presenter;


import com.zqx.osc_mvp_demo.ui.view.NetDataView;
import com.zqx.osc_mvp_demo.util.Run;

/**
 * Created by ZhangQixiang on 2017/3/8.
 * <p>
 * 所有的逻辑为 [获取网络数据,解析为bean,并通知View] 的presenter的父类
 * T: bean 的类型
 */

public abstract class NetBeanPresenter<T> implements INetDataPresenter<T> {

    protected NetDataView mNetDataView;

    public NetBeanPresenter(NetDataView netDataView) {
        mNetDataView = netDataView;
    }

    @Override
    public void getData(final int requestCode, final String url, final Class<T> beanClass) {
        Run.onSub(new Runnable() {
            @Override
            public void run() {
                final T data = onGetData(requestCode, url, beanClass);

                Run.onMain(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            mNetDataView.onDataSuccess(requestCode, data);
                        } else {
                            mNetDataView.onDataFailed(requestCode);
                        }

                    }
                });

            }
        });
    }


    protected abstract T onGetData(int requestCode, String url, Class<T> beanClass);


}
