package com.zqx.osc_mvp_demo.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.zqx.osc_mvp_demo.ui.adapter.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangQixiang on 2017/2/25.
 * 泛型给子条目的类型
 */
public class SimpleRecyclerView<T> extends RecyclerView {

    private List<T> mDatas      = new ArrayList<>();
    private int     mItemLayout = -1;
    private SimpleRecyclerAdapter<T> mAdapter;

    public SimpleRecyclerView(Context context) {
        this(context, null);
    }

    public SimpleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

    }

    public SimpleRecyclerView addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("SimpleRecyclerView试图添加一个null的集合!");
        }
        mDatas.addAll(list);
        return this;
    }

    public SimpleRecyclerView addItem(T item) {
        mDatas.add(item);
        return this;
    }

    public SimpleRecyclerView clear() {
        mDatas.clear();
        return this;
    }

    public SimpleRecyclerView itemLayout(int itemLayout) {
        mItemLayout = itemLayout;
        return this;
    }

    public SimpleRecyclerView bindItemBy(SimpleRecyclerAdapter.SimpleAdapterListener<T> binder) {
        if (mItemLayout == -1) {
            throw new RuntimeException("SimpleRecyclerView没有配置条目布局!");
        }
        mAdapter = new SimpleRecyclerAdapter<>(mDatas, mItemLayout, binder);
        setAdapter(mAdapter);
        return this;
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void notifyItemMoved(int from, int to) {
        mAdapter.notifyItemMoved(from, to);
    }

    public void notifyItemRemoved(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    public void notifyItemInserted(int position) {
        mAdapter.notifyItemInserted(position);
    }

    //其他方法代理...
}
