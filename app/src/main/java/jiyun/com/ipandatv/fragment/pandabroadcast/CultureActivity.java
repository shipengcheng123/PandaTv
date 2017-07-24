package jiyun.com.ipandatv.fragment.pandabroadcast;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class CultureActivity extends BaseActivity {

    @BindView(R.id.custom_videoplayer_standard_with_share_button)
    JCVideoPlayerStandard customVideoplayerStandardWithShareButton;


    @Override
    protected int getLayoutId() {
        return R.layout.video_avtivity;
    }

    @Override
    protected void initView() {


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        MyLog.e("url",url+title);

        //标准基础上改进的视频播放(添加了分享按钮)
        customVideoplayerStandardWithShareButton = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard_with_share_button);
        customVideoplayerStandardWithShareButton.setUp(url,JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,title);
        customVideoplayerStandardWithShareButton.startVideo();
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
}
