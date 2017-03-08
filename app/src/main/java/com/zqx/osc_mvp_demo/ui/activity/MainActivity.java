package com.zqx.osc_mvp_demo.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.zqx.osc_mvp_demo.R;
import com.zqx.osc_mvp_demo.bean.QuestionBean;
import com.zqx.osc_mvp_demo.global.Urls;
import com.zqx.osc_mvp_demo.presenter.JsonBeanPresenter;
import com.zqx.osc_mvp_demo.ui.adapter.SimpleRecyclerAdapter;
import com.zqx.osc_mvp_demo.ui.view.NetDataView;
import com.zqx.osc_mvp_demo.ui.widget.SimpleRecyclerView;
import com.zqx.osc_mvp_demo.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements SimpleRecyclerAdapter.SimpleAdapterListener<QuestionBean.ResultBean.Item>,
        SwipeRefreshLayout.OnRefreshListener, NetDataView<QuestionBean> {

    @BindView(R.id.recycler)
    SimpleRecyclerView mRecycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    private JsonBeanPresenter<QuestionBean> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecycler.itemLayout(R.layout.item_question).bindItemBy(this);
        mSwipe.setColorSchemeColors(Color.GREEN, Color.RED);
        mSwipe.setOnRefreshListener(this);
        mPresenter = new JsonBeanPresenter<>(this);
        onRefresh();
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolder holder, QuestionBean.ResultBean.Item item) {
        Glide.with(this)
                .load(item.authorPortrait)
                .error(R.mipmap.ic_launcher)
                .into(holder.getImageView(R.id.iv_icon));

        holder.getTextView(R.id.tv_title).setText(item.title);
        holder.getTextView(R.id.tv_desc).setText(item.body);

    }

    @Override
    public void onRefresh() {
        mPresenter.getData(1,Urls.QUESTION,QuestionBean.class);
    }

    @Override
    public void onDataSuccess(int requestCode, QuestionBean dataBean) {
        mRecycler.addAll(dataBean.result.items).notifyDataSetChanged();
        mSwipe.setRefreshing(false);
    }

    @Override
    public void onDataFailed(int requestCode) {
        ToastUtil.show("获取数据失败");
    }
}
