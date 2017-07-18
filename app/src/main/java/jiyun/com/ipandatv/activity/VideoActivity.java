package jiyun.com.ipandatv.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.fragment.pandadirect.bean.VedioJCYKBean;
import jiyun.com.ipandatv.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

/**
 * Created by Lenovo on 2017/7/13.
 */
public class VideoActivity extends BaseActivity implements VideoContract.View{


    @BindView(R.id.custom_videoplayer_standard_with_share_button)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen customVideoplayerStandardWithShareButton;

    private VideoContract.Presenter presenter;
    private String pid, title;;
    @Override
    protected int getLayoutId() {
        return R.layout.video_avtivity;
    }

    @Override
    protected void initView() {

        VideoPresenter presenter=new VideoPresenter(this);
        Intent intent = getIntent();

        pid = intent.getStringExtra("pid");
//        String url = intent.getStringExtra("url");
//        String url="hd;

        title = intent.getStringExtra("title");
//        MyLog.e("url",url+title);

        //标准基础上改进的视频播放(添加了分享按钮)
        customVideoplayerStandardWithShareButton = (JCVideoPlayerStandardShowShareButtonAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_with_share_button);
//        customVideoplayerStandardWithShareButton.setUrlAndObject(url, null,title);
//        ImageLoader.getInstance().displayImage("http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
//                customVideoplayerStandardWithShareButton.ivThumb);
    }


//    @Override
//    protected void onPause() {
//        super.onPause();
//        JCVideoPlayer.releaseAllVideos();
//    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {
        presenter.setVideoURl(pid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void showlivevedioFragment(VedioJCYKBean jcykBean) {

        List<VedioJCYKBean.VideoBean.Chapters2Bean> chapters2 = jcykBean.getVideo().getChapters2();
        String url = chapters2.get(0).getUrl();
        customVideoplayerStandardWithShareButton.setUrlAndObject(url, null,title);
    }

    @Override
    public void setBasePresenter(VideoContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
