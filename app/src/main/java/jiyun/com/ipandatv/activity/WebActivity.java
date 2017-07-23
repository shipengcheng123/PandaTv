package jiyun.com.ipandatv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by Lenovo on 2017/7/13.
 */
public class WebActivity extends BaseActivity {

    @BindView(R.id.WebView)
    WebView WebView;
    @BindView(R.id.Shoucang)
    CheckBox Shoucang;
    @BindView(R.id.Fenxiang)
    ImageView Fenxiang;
    private String rurl;

    @Override
    protected int getLayoutId() {
        return R.layout.webview_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        rurl = intent.getStringExtra("url");
        MyLog.e("URL",rurl);


        WebView.loadUrl(rurl);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        WebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.Shoucang, R.id.Fenxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Shoucang:
                break;
            case R.id.Fenxiang:
                break;
        }
    }
}
