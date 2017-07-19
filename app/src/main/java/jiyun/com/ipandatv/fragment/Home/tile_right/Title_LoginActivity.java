package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;

/**
 * Created by lx on 2017/7/14.
 */

public class Title_LoginActivity extends BaseActivity {
    @BindView(R.id.Login_Finish)
    ImageView LoginFinish;
    @BindView(R.id.Login_Register)
    TextView LoginRegister;
    @BindView(R.id.Text_Login)
    TextView TextLogin;
    @BindView(R.id.Login_WeiXin)
    ImageView LoginWeiXin;
    @BindView(R.id.Login_QQ)
    ImageView LoginQQ;
    @BindView(R.id.Login_WeiBo)
    ImageView LoginWeiBo;
    @BindView(R.id.mText)
    TextView mText;
    @BindView(R.id.editUserName)
    EditText editUserName;
    @BindView(R.id.editUserPassword)
    EditText editUserPassword;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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

    @OnClick(R.id.Login_Finish)
    public void onViewClicked() {
        finish();
    }
}
