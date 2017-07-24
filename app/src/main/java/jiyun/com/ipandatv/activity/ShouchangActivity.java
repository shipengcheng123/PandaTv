package jiyun.com.ipandatv.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.fragment.shoucahng.KandianFragment;
import jiyun.com.ipandatv.fragment.shoucahng.ShouchangzhiboFragment;

/**
 * Created by Lenovo on 2017/7/24.
 */
public class ShouchangActivity extends BaseActivity {
    @BindView(R.id.Back)
    ImageView Back;
    @BindView(R.id.Bianji)
    TextView Bianji;
    @BindView(R.id.Shouchang_zhibo)
    RadioButton ShouchangZhibo;
    @BindView(R.id.shoucahng_kandian)
    RadioButton shoucahngKandian;
    @BindView(R.id.Shouchang_Framelayout)
    FrameLayout ShouchangFramelayout;
    private FragmentManager manager;
    @Override
    protected int getLayoutId() {
        return R.layout.shouchang_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.Shouchang_Framelayout, new ShouchangzhiboFragment());
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Back, R.id.Bianji, R.id.Shouchang_zhibo, R.id.shoucahng_kandian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Back:
                onBackPressed();
                break;
            case R.id.Bianji:


                break;
            case R.id.Shouchang_zhibo:
                manager = getSupportFragmentManager();
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.Shouchang_Framelayout, new ShouchangzhiboFragment());
                transaction1.commit();
                Bianji.setVisibility(View.GONE);
                break;
            case R.id.shoucahng_kandian:
                Bianji.setVisibility(View.VISIBLE);
                manager = getSupportFragmentManager();
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.replace(R.id.Shouchang_Framelayout, new KandianFragment(Bianji));
                transaction2.commit();
                break;
        }
    }
}
