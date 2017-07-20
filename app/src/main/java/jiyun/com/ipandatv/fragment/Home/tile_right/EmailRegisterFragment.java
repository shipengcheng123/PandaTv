package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lx on 2017/7/20.
 */

public class EmailRegisterFragment extends BaseFragment {
    @BindView(R.id.editUserPhone_yx)
    EditText editUserPhoneYx;
    @BindView(R.id.checkCodeEdit_yx)
    EditText checkCodeEditYx;
    @BindView(R.id.checkCodeImage_yx)
    ImageView checkCodeImageYx;
    @BindView(R.id.newPasswordEdit_yx)
    EditText newPasswordEditYx;
    @BindView(R.id.newCheckPasswordEdit_yx)
    EditText newCheckPasswordEditYx;
    @BindView(R.id.findPwdBtn_yx)
    Button findPwdBtnYx;
    private byte[] bytes;

    @Override
    protected int getLayoutId() {
        return R.layout.register_youxiang;
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
                        for (int x = 0; x < headers.size(); x++) {
                            String name = headers.name(x);
                            String name1 = headers.get(name);
                            if (name1.equals("JSESSIONID")) {

                            }
                            Log.e("TAG", name + "---");
                        }

                        bytes = response.body().bytes();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Drawable captchaImage = byteToDrawable(bytes);
                                checkCodeImageYx.setImageDrawable(captchaImage);
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

    @OnClick({R.id.checkCodeImage_yx, R.id.findPwdBtn_yx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkCodeImage_yx:
                getPersonalRegImgCheck();
                break;
            case R.id.findPwdBtn_yx:
                break;
        }
    }
}
