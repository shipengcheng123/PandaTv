package jiyun.com.ipandatv.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.TotalList.broadcast.BobaoFragment;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.config.ConfigFragment;
import jiyun.com.ipandatv.fragment.Home.HomeFragment;
import jiyun.com.ipandatv.fragment.Home.HomePresenter;
import jiyun.com.ipandatv.fragment.pandadirect.PandadirectFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.FrameLayout)
    android.widget.FrameLayout FrameLayout;
    @BindView(R.id.btn_explore_comprehensive)
    RadioButton btnExploreComprehensive;
    @BindView(R.id.btn_explore_move)
    RadioButton btnExploreMove;
    @BindView(R.id.btn_explore_plus)
    RadioButton btnExplorePlus;
    @BindView(R.id.btn_explore_find)
    RadioButton btnExploreFind;
    @BindView(R.id.btn_explore_my)
    RadioButton btnExploreMy;
    @BindView(R.id.FrameLayout_contentGroup)
    RadioGroup FrameLayoutContentGroup;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentManager fragmentmanager;
    private HomeFragment homeFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fragmentmanager = getSupportFragmentManager();
        App.mRadiogroup = (RadioGroup) findViewById(R.id.FrameLayout_contentGroup);
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        homeFragment = new HomeFragment();
        new HomePresenter(homeFragment);
        fragmentmanager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentmanager.beginTransaction();
        transaction.add(R.id.FrameLayout, homeFragment, "HomeFragment");
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_explore_comprehensive, R.id.btn_explore_move, R.id.btn_explore_plus, R.id.btn_explore_find, R.id.btn_explore_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explore_comprehensive:
                initView();
                ConfigFragment.getInstance().init().start(HomeFragment.class).build();
                break;
            case R.id.btn_explore_move:
                initView();
                ConfigFragment.getInstance().init().start(PandadirectFragment.class).build();
                break;
            case R.id.btn_explore_plus:
                break;
            case R.id.btn_explore_find:
                BaseFragment build = ConfigFragment.getInstance().init().start(BobaoFragment.class).build();



                break;
            case R.id.btn_explore_my:
                break;
        }
    }
}
