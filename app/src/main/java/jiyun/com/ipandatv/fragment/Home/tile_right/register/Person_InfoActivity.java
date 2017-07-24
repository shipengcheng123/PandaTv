package jiyun.com.ipandatv.fragment.Home.tile_right.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.model.entity.LoginBean;

/**
 * Created by lx on 2017/7/22.
 */

public class Person_InfoActivity extends BaseActivity {
    @BindView(R.id.entry)
    ImageView entry;
    @BindView(R.id.panda_person_userimg)
    ImageView pandaPersonUserimg;
    @BindView(R.id.panda_person_img)
    RelativeLayout pandaPersonImg;
    @BindView(R.id.entry1)
    ImageView entry1;
    @BindView(R.id.panda_person_guankan)
    TextView pandaPersonGuankan;
    @BindView(R.id.panda_person_nicheng)
    RelativeLayout pandaPersonNicheng;
    @BindView(R.id.panda_person_tuichu)
    Button pandaPersonTuichu;
    @BindView(R.id.Login_Finish_Person)
    ImageView LoginFinishPerson;
    private LoginBean loginBean;

    @Override
    protected int getLayoutId() {
        return R.layout.panda_person_personinfo;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        Intent in = getIntent();
        ACache acache = ACache.get(this);
        loginBean = (LoginBean) acache.getAsObject("loginentity");
        String userName = in.getStringExtra("userName");
        if (userName.equals("点击登录")) {

        } else {
            pandaPersonGuankan.setText(userName);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.Login_Finish_Person, R.id.panda_person_nicheng, R.id.panda_person_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_Finish_Person:
                finish();
                break;
            case R.id.panda_person_nicheng:
                Intent intent = new Intent(this, PersonAmendActivity.class);
                String s = pandaPersonGuankan.getText().toString();
                intent.putExtra("info", s);
                startActivity(intent);
                break;
            case R.id.panda_person_tuichu:
                ACache acache = ACache.get(this);
                acache.remove("loginentity");
                Intent in = getIntent();
                in.putExtra("edit", "点击登录");
                setResult(300, in);
                finish();
                break;
        }
    }
}
