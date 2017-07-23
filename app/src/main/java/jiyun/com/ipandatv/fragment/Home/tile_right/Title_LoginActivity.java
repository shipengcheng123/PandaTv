package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.model.entity.LoginBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
    private ProgressDialog dialog;
    OkHttpClient client = new OkHttpClient.Builder().build();
    String mUserSeqId, errType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        dialog = new ProgressDialog(this);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.Login_QQ, R.id.Login_WeiBo, R.id.Login_Finish, R.id.Login_Register, R.id.loginBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Login_QQ:
                //TODO QQ授权登陆
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.Login_WeiBo:
                UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.SINA, authListener);
                break;
            case R.id.Login_Finish:
                finish();
                break;
            case R.id.loginBtn:
                Login();
                finish();
                break;
            case R.id.Login_Register:
                Intent in = new Intent(this, Title_RegisterActivity.class);
                startActivity(in);
                break;
        }
    }

    private void Login() {
        String userName = editUserName.getText().toString().trim();
        String passWard = editUserPassword.getText().toString().trim();

        //http://cbox_mobile.regclientuser.cntv.cn
        String from = "https://reg.cntv.cn/login/login.action";
        try {
            String url = from + "?username="
                    + URLEncoder.encode(userName, "UTF-8")
                    + "&password=" + passWard
                    + "&service=client_transaction" + "&from="
                    + URLEncoder.encode(from, "UTF-8");

            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CYNTV_MOBILE", "UTF-8"))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("TAG", e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String string = response.body().string();

                    Log.i("TAG", "登录成功：：" + string);

                    Gson gson = new Gson();

                    LoginBean loginBean = gson.fromJson(string, LoginBean.class);

                    errType = loginBean.getErrType();

                    mUserSeqId = loginBean.getUser_seq_id();

                    Headers headers = response.headers();

                    for (int i = 0; i < headers.size(); i++) {

                        String name = headers.name(i);

                        Log.i("TAG", "登录打印cookie值：：" + name);

                    }
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    UMAuthListener authListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(Title_LoginActivity.this, "成功了" + data, Toast.LENGTH_LONG).show();
            Set<String> keySet = data.keySet();
            String s;
            for (String key : keySet) {
                s = data.get(key);
                Log.i("===========", data + "");
            }
//            String name = data.get("name");
//            String iconurl = data.get("iconurl");
//            Log.i("==", name + iconurl);
//            Intent in = getIntent();
//            in.putExtra("name", name);
//            in.putExtra("iconurl", iconurl);
//            setResult(0, in);
//            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(Title_LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(Title_LoginActivity.this, "取消授权", Toast.LENGTH_LONG).show();
        }
    };

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            Log.i("==========", data + "");
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

}
