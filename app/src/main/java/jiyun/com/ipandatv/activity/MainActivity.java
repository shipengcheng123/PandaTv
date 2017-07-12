package jiyun.com.ipandatv.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.TotalList.TotalPage.HomeFragment;
import jiyun.com.ipandatv.TotalList.TotalPage.HomePresenter;
import jiyun.com.ipandatv.base.BaseActivity;

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
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private HomeFragment homeFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        homeFragment = new HomeFragment();
        new HomePresenter(homeFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.FrameLayout, homeFragment, "HomeFragment");
        transaction.commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.btn_explore_comprehensive, R.id.btn_explore_move, R.id.btn_explore_plus, R.id.btn_explore_find, R.id.btn_explore_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explore_comprehensive:
                break;
            case R.id.btn_explore_move:
                break;
            case R.id.btn_explore_plus:
                break;
            case R.id.btn_explore_find:
                break;
            case R.id.btn_explore_my:
                break;
        }
    }
}
