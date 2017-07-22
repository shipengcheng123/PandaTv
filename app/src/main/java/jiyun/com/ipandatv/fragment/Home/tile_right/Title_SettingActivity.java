package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.utils.CleanMessageUtil;

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
        try {
            number.setText(CleanMessageUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.Setting_Finish, R.id.clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Setting_Finish:
                finish();
                break;
            case R.id.clean:
                onClickCleanCache();
                break;
        }
    }

    private void onClickCleanCache() {
        getConfirmDialog(this, "是否清空缓存?", new DialogInterface.OnClickListener
                () {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                CleanMessageUtil.clearAllCache(App.activity);
                number.setText("0 k");
            }
        }).show();
    }

    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }
}
