package jiyun.com.ipandatv.fragment.Home.tile_right.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.view.AmendEditText;

/**
 * Created by lx on 2017/7/24.
 */

public class PersonAmendActivity extends BaseActivity {
    @BindView(R.id.Amend_Login_Finish_Person)
    ImageView LoginFinishPerson;
    @BindView(R.id.Edit_Amend)
    AmendEditText EditAmend;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_amend;
    }

    @Override
    protected void initView() {
        Intent in = getIntent();
        String info = in.getStringExtra("info");
        EditAmend.setText(info);
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

    @OnClick(R.id.Amend_Login_Finish_Person)
    public void onViewClicked() {
        finish();
    }
}
