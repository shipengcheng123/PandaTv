package jiyun.com.ipandatv.fragment.Home.tile_right;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;

/**
 * Created by lx on 2017/7/17.
 */

public class BobaoActivity extends BaseActivity {
    @BindView(R.id.panda_News_WebView)
    WebView WebViewMain;
    @BindView(R.id.original_Back_Image)
    ImageView originalBackImage;
    @BindView(R.id.ShouCang)
    TextView ShouCang;

    @Override
    protected int getLayoutId() {
        return R.layout.bobaowebview;
    }

    @Override
    protected void initView() {
        Intent in = getIntent();
        String url = in.getStringExtra("url");
        WebViewMain.loadUrl(url);
        WebViewMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
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

    @OnClick({R.id.original_Back_Image, R.id.ShouCang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.original_Back_Image:
                break;
            case R.id.ShouCang:
                break;
        }
    }

}
