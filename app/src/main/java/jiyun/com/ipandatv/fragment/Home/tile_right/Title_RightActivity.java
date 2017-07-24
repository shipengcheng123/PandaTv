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
import jiyun.com.ipandatv.activity.ShouchangActivity;
import jiyun.com.ipandatv.base.BaseActivity;

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
    @BindView(R.id.Text_Person)
    TextView TextPerson;

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
//                Log.i("00000000", TextPerson.getText().toString());
//                boolean equals = TextPerson.getText().toString().equals("点击登录");
//                if (equals = false) {
//                    Intent in = new Intent(Title_RightActivity.this, BobaoActivity.class);
//                    startActivity(in);
//                } else if (equals = true) {
                Intent in = new Intent(Title_RightActivity.this, Title_LoginActivity.class);
                startActivityForResult(in, 0);
//                }
                break;
            case R.id.panda_person_guankanlishi:
                Intent intent = new Intent(Title_RightActivity.this,LishiJiLuActivity.class);
                startActivity(intent);
                break;
            case R.id.panda_person_wodeshoucang:
                Intent intent1 = new Intent(Title_RightActivity.this,ShouchangActivity.class);
                startActivity(intent1);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == 0) {
//            String name = data.getStringExtra("name");
//            String iconurl = data.getStringExtra("iconurl");
//            TextPerson.setText(name);
//            Glide.with(getApplicationContext()).load(iconurl).into(pandaPersonUserimg);
//        }
//    }
}
