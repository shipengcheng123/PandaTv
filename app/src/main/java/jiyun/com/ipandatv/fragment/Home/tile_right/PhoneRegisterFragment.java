package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lx on 2017/7/20.
 */

public class PhoneRegisterFragment extends BaseFragment {
    @BindView(R.id.editUserPhone_)
    EditText editUserPhone;
    @BindView(R.id.checkCodeEdit_)
    EditText checkCodeEdit;
    @BindView(R.id.checkCodeImage_)
    ImageView checkCodeImage;
    @BindView(R.id.phoneCheckCode_)
    EditText phoneCheckCode;
    @BindView(R.id.phoneCheckCodeBtn_)
    Button phoneCheckCodeBtn;
    @BindView(R.id.newPasswordEdit_)
    EditText newPasswordEdit;
    @BindView(R.id.findPwdBtn_)
    Button findPwdBtn;
    @BindView(R.id.Phone_Cancle)
    TextView PhoneCancle;
    @BindView(R.id.Photo_Cancle)
    TextView PhotoCancle;
    @BindView(R.id.Yzm_Cancle)
    TextView YzmCancle;
    @BindView(R.id.Pwd_Cancle)
    TextView PwdCancle;
    private byte[] bytes;
    private OkHttpClient client = new OkHttpClient();
    private String jsonId;

    @Override
    protected int getLayoutId() {
        return R.layout.register_phone;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        getPersonalRegImgCheck();
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @OnClick({R.id.checkCodeImage_, R.id.phoneCheckCodeBtn_, R.id.findPwdBtn_})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkCodeImage_:
                //图形验证码点击改变
                getPersonalRegImgCheck();
                break;
            case R.id.phoneCheckCodeBtn_:
                //手机验证码
                getPhoneCode();
                break;
            case R.id.findPwdBtn_:
                //注册
                getRegister();
                break;
        }
    }

    //图形验证码
    public void getPersonalRegImgCheck() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request build = new Request.Builder().url("http://reg.cntv.cn/simple/verificationCode.action").build();
                okHttpClient.newCall(build).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Headers headers = response.headers();
                        jsonId = headers.get("Set-Cookie");
                        bytes = response.body().bytes();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Drawable captchaImage = byteToDrawable(bytes);
                                checkCodeImage.setImageDrawable(captchaImage);
                            }
                        });
                    }
                });
            }
        }).start();
    }

    public static Drawable byteToDrawable(byte[] byteArray) {
        try {
            String string = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }

    //获取手机验证码
    public void getPhoneCode() {
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
        //手机号
        String tPhoneNumber = editUserPhone.getText().toString().trim();
        //验证码
        String imgyanzhengma = checkCodeEdit.getText().toString().trim();
        RequestBody body = new FormBody.Builder()
                .add("method", "getRequestVerifiCodeM")
                .add("mobile", tPhoneNumber)
                .add("verfiCodeType", "1")
                .add("verificationCode", imgyanzhengma)
                .build();

        try {
            Request request = new Request.Builder().url(url)
                    .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .addHeader("Cookie", jsonId)
                    .post(body).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("TAG", "失败");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Log.i("TAG", "手机验证码打印：" + string);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //注册
    public void getRegister() {
        //注册url
        String url = "https://reg.cntv.cn/regist/mobileRegist.do";
        //手机号输入
        String number = editUserPhone.getText().toString().trim();
        //手机验证码
        String tCheckPhone = phoneCheckCode.getText().toString().trim();
        //密码输入
        String tPassWord = newPasswordEdit.getText().toString();
        try {
            RequestBody body = new FormBody.Builder()
                    .add("method", "saveMobileRegisterM")
                    .add("mobile", number)
                    .add("verfiCode", tCheckPhone)
                    .add("passWd", URLEncoder.encode(tPassWord, "UTF-8"))
                    .add("verfiCodeType", "1")
                    .add("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                    .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("TAG", e.getMessage().toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String loginSate = response.body().string();
                    Log.i("TAG", "注册状态：：" + loginSate);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
