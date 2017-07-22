package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
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
    @BindView(R.id.newPasswordEdit_yx)
    EditText newPasswordEditYx;
    @BindView(R.id.newCheckPasswordEdit_yx)
    EditText newCheckPasswordEditYx;
    @BindView(R.id.findPwdBtn_yx)
    Button findPwdBtnYx;
    @BindView(R.id.Email_Image_YanZhengma)
    ImageView EmailImageYanZhengma;
    Unbinder unbinder;
    private byte[] bytes;
    private String jsonId;

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
                        jsonId = headers.get("Set-Cookie");
                        bytes = response.body().bytes();
                        App.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Drawable captchaImage = byteToDrawable(bytes);
                                EmailImageYanZhengma.setImageDrawable(captchaImage);
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

    @OnClick({R.id.findPwdBtn_yx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.findPwdBtn_yx:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Email_Image_YanZhengma)
    public void onViewClicked() {
        getPersonalRegImgCheck();
    }
}
