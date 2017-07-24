package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.fragment.Home.tile_right.login.Title_LoginActivity;
import jiyun.com.ipandatv.fragment.Home.tile_right.register.Person_InfoActivity;
import jiyun.com.ipandatv.model.entity.LoginBean;
import jiyun.com.ipandatv.utils.MyLog;

import static jiyun.com.ipandatv.R.id.Text_Person;


/**
 * Created by lx on 2017/7/14.
 */

public class Title_RightActivity extends BaseActivity {
    @BindView(R.id.panda_person_userimg)
    ImageView pandaPersonUserimg;
    @BindView(R.id.panda_person_username)
    RelativeLayout pandaPersonUsername;
    @BindView(R.id.panda_person_guankan)
    ImageView pandaPersonGuankan;
    @BindView(R.id.panda_person_guankanlishi)
    RelativeLayout pandaPersonGuankanlishi;
    @BindView(R.id.panda_person_shoucang)
    ImageView pandaPersonShoucang;
    @BindView(R.id.panda_person_wodeshoucang)
    RelativeLayout pandaPersonWodeshoucang;
    @BindView(R.id.panda_person_set)
    ImageView pandaPersonSet;
    @BindView(R.id.panda_person_setting)
    RelativeLayout pandaPersonSetting;
    @BindView(R.id.Personal_Finish)
    ImageView PersonalFinish;
    @BindView(Text_Person)
    TextView TextPerson;
    private String name;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.panda_person_view;
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

    @OnClick({R.id.panda_person_username, R.id.panda_person_guankanlishi, R.id.panda_person_wodeshoucang, R.id.panda_person_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.panda_person_username:
                ACache acache = ACache.get(this);
                loginBean = (LoginBean) acache.getAsObject("loginentity");
                if (loginBean == null && TextPerson.getText().toString().equals("点击登录")) {
                    Intent in = new Intent(Title_RightActivity.this, Title_LoginActivity.class);
                    startActivityForResult(in, 50);
                } else {
                    Intent in = new Intent(Title_RightActivity.this, Person_InfoActivity.class);
                    in.putExtra("userName", TextPerson.getText().toString());
                    startActivityForResult(in,300);
                }
                break;
            case R.id.panda_person_guankanlishi:

                break;
            case R.id.panda_person_wodeshoucang:

                break;
            case R.id.panda_person_setting:
                Intent ini = new Intent(Title_RightActivity.this, Title_SettingActivity.class);
                startActivity(ini);
                break;
        }
    }

    @OnClick(R.id.Personal_Finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 10:
                name = data.getStringExtra("name");
                MyLog.e("TAB", name);
                TextPerson.setText(name);
                break;
            case 50:
                TextPerson.setText(data.getStringExtra("user"));
                MyLog.e("TAG", data.getStringExtra("user"));
                break;
            case 300:
                TextPerson.setText(data.getStringExtra("edit"));
                MyLog.e("TAG", data.getStringExtra("edit"));
                break;
        }
    }
}
