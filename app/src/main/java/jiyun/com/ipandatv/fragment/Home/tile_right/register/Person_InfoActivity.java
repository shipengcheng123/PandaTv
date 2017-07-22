package jiyun.com.ipandatv.fragment.Home.tile_right.register;

import android.content.Intent;
import android.os.Bundle;
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
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.panda_person_tuichu)
    public void onViewClicked() {
        ACache acache = ACache.get(this);
        acache.remove("loginentity");
        acache.remove("UmDsf");
        finish();
        Intent in = new Intent(Person_InfoActivity.this, Title_RightActivity.class);
        in.putExtra("edit", "点击登陆");
        setResult(300, in);
    }
}
