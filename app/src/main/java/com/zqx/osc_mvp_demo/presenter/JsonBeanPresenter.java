package com.zqx.osc_mvp_demo.presenter;


import com.zqx.osc_mvp_demo.bussiness.JsonDataManager;
import com.zqx.osc_mvp_demo.ui.view.NetDataView;

/**
 * Created by ZhangQixiang on 2017/3/3.
 *
 * 业务逻辑: 获取Json数据, 解析为bean, 通知view层更新ui<br/>
 * T: 所获取的数据最外层的bean类型
 */
public class JsonBeanPresenter<T> extends NetBeanPresenter<T> {

    public JsonBeanPresenter(NetDataView netDataView) {
        super(netDataView);
    }

    @Override
    protected T onGetData(int requestCode, String url, Class<T> beanClass) {
        return JsonDataManager.getDataBean(url,beanClass);
    }

}
