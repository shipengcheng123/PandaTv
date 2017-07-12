package jiyun.com.ipandatv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import jiyun.com.ipandatv.App;

/**
 * Created by lx on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.context = this;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        loadData();
    }

    //加载布局
    protected abstract int getLayoutId();

    //初始化view
    protected abstract void initView();

    //初始化数据
    public abstract void initData();

    //加载数据
    public abstract void loadData();
}
