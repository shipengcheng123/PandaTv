package jiyun.com.ipandatv.fragment.Home.tile_right.forgetpwd;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;

/**
 * Created by lx on 2017/7/24.
 */

public class ForgetActivity extends BaseActivity implements ForgetContract.View {
    @BindView(R.id.Login_Finish)
    ImageView LoginFinish;
    @BindView(R.id.forget_editUserPhone)
    EditText forgetEditUserPhone;
    @BindView(R.id.forget_Phone_Cancle)
    TextView forgetPhoneCancle;
    @BindView(R.id.forget_checkCodeEdit_)
    EditText forgetCheckCodeEdit;
    @BindView(R.id.forget_TPYanZhengma)
    ImageView forgetTPYanZhengma;
    @BindView(R.id.Photo_Cancle)
    TextView PhotoCancle;
    @BindView(R.id.forget_phoneCheckCode_)
    EditText forgetPhoneCheckCode;
    @BindView(R.id.forget_phoneCheckCodeBtn_)
    Button forgetPhoneCheckCodeBtn;
    @BindView(R.id.Yzm_Cancle)
    TextView YzmCancle;
    @BindView(R.id.forget_newPasswordEdit_)
    EditText forgetNewPasswordEdit;
    @BindView(R.id.Pwd_Cancle)
    TextView PwdCancle;
    @BindView(R.id.forget_findPwdBtn)
    Button forgetFindPwdBtn;
    private ForgetContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.forgetactivity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        new ForgetPresenter(this);
        presenter.start();
    }

    @Override
    public void setDrawable(Drawable drawable) {
        forgetTPYanZhengma.setImageDrawable(drawable);
    }

    @Override
    public void sendYam(String s) {
        Toast.makeText(ForgetActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBasePresenter(ForgetContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Login_Finish, R.id.forget_TPYanZhengma, R.id.forget_phoneCheckCodeBtn_, R.id.forget_findPwdBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_Finish:
                finish();
                break;
            case R.id.forget_TPYanZhengma:
                presenter.start();
                break;
            case R.id.forget_phoneCheckCodeBtn_:
                //手机号
                String tPhoneNumber = forgetEditUserPhone.getText().toString().trim();
                //图片验证码
                String imgyanzhengma = forgetCheckCodeEdit.getText().toString().trim();
                //手机验证码
                presenter.getSjYam(tPhoneNumber, imgyanzhengma);
                break;
            case R.id.forget_findPwdBtn:
                //手机号输入
                String number = forgetEditUserPhone.getText().toString().trim();
                //手机验证码
                String tCheckPhone = forgetPhoneCheckCode.getText().toString().trim();
                //密码输入
                String tPassWord = forgetNewPasswordEdit.getText().toString();
                //修改密码
                presenter.forgetPwd(number, tCheckPhone, tPassWord);
                break;
        }
    }
}
