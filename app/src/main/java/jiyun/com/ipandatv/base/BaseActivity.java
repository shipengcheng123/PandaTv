package jiyun.com.ipandatv.base;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import jiyun.com.ipandatv.App;

/**
 * Created by lx on 2017/7/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        App.activity = this;
        initView();
        initData();
        loadData();
        if (ContextCompat.checkSelfPermission(BaseActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BaseActivity.this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.VIBRATE}, 1);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //记住当前的BaseActivity
        App.activity = this;
        //当页面可见时加载数据
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
