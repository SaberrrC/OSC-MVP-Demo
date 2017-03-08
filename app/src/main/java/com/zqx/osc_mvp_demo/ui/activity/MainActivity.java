package com.zqx.osc_mvp_demo.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.zqx.osc_mvp_demo.R;
import com.zqx.osc_mvp_demo.ui.widget.SimpleRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    SimpleRecyclerView mRecycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecycler.itemLayout(R.layout.item_ask_answer);
    }
}
