package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.config.ConfigFragment;




/**
 * Created by lx on 2017/7/14.
 */

public class Title_SettingActivity extends BaseActivity {
    @BindView(R.id.Setting_Finish)
    ImageView SettingFinish;
    @BindView(R.id.isPush)
    CheckBox isPush;
    @BindView(R.id.isPlay)
    CheckBox isPlay;
    @BindView(R.id.personal_delete_img)
    ImageView personalDeleteImg;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.clean)
    RelativeLayout clean;
    @BindView(R.id.panda_setting_help)
    RelativeLayout pandaSettingHelp;
    @BindView(R.id.isShengji)
    ImageView isShengji;
    @BindView(R.id.panda_setting_shengji)
    RelativeLayout pandaSettingShengji;
    @BindView(R.id.isGood)
    ImageView isGood;
    @BindView(R.id.panda_setting_haoping)
    RelativeLayout pandaSettingHaoping;
    @BindView(R.id.isAbout)
    ImageView isAbout;
    @BindView(R.id.panda_setting_about)
    RelativeLayout pandaSettingAbout;
    @BindView(R.id.person_fankui)
    TextView personFankui;

    @Override
    protected int getLayoutId() {
        return R.layout.panda_person_setting;
    }

    @Override
    protected void initView() {

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Setting_Finish)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.panda_setting_help, R.id.panda_setting_shengji, R.id.panda_setting_haoping, R.id.panda_setting_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.panda_setting_help:
                ConfigFragment.getInstance().init().start(PersonActivity.class).build();
                break;
            case R.id.panda_setting_shengji:
                Toast.makeText(Title_SettingActivity.this, "已经是最新版本了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.panda_setting_haoping:
                break;
            case R.id.panda_setting_about:
                break;
        }
    }
}
