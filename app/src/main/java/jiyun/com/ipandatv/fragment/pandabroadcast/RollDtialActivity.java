package jiyun.com.ipandatv.fragment.pandabroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.fragment.pandabroadcast.adapter.PandaDetailAdapter;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaCultureVedioBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaTebieBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.CultureContract;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.PandaCultureVideoPresenter;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;
import jiyun.com.ipandatv.view.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

public class RollDtialActivity extends BaseActivity implements CultureContract.View {

    @BindView(R.id.goback_butt)
    ImageView gobackButt;
    @BindView(R.id.custom_videoplayer_standard_with_share_button)
    JCVideoPlayerStandardShowShareButtonAfterFullscreen customVideoplayerStandardWithShareButton;
    @BindView(R.id.rollvideo_time_text)
    TextView rollvideoTimeText;
    @BindView(R.id.rollvideo_jieshao_text)
    TextView rollvideoJieshaoText;
    @BindView(R.id.rollvideo_details_show_image)
    ImageView rollvideoDetailsShowImage;
    @BindView(R.id.detiles_text)
    TextView detilesText;
    @BindView(R.id.visibility_linear)
    LinearLayout visibilityLinear;
    @BindView(R.id.detils_pullto)
    PullToRefreshRecyclerView detilsPullto;
    @BindView(R.id.collect_no)
    ImageView collectNo;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    private CultureContract.Presenter presenter;
    private String pid, title;
    private PandaDetailAdapter adapter;
    private List<PandaTebieBean.VideoBean> mlist = new ArrayList<>();
    private int Index = 1;
    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.rollvideo_details;
    }

    @Override
    protected void initView() {
        new PandaCultureVideoPresenter(this);
        Intent intent = getIntent();

        pid = intent.getStringExtra("pid");

        title = intent.getStringExtra("title");


        //标准基础上改进的视频播放(添加了分享按钮)
        customVideoplayerStandardWithShareButton = (JCVideoPlayerStandardShowShareButtonAfterFullscreen) findViewById(R.id.custom_videoplayer_standard_with_share_button);
        LinearLayoutManager layoutManager = new LinearLayoutManager(App.context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detilsPullto.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        detilsPullto.setLayoutManager(layoutManager);
        detilsPullto.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        detilsPullto.setLoadingMoreEnabled(true);
        //开启刷新回调
        detilsPullto.displayLastRefreshTime(true);
        //停止刷新
        //停止刷新
        detilsPullto.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                detilsPullto.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        detilsPullto.setRefreshComplete();
                        mlist.clear();
                        loadData();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                detilsPullto.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        detilsPullto.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });
        adapter = new PandaDetailAdapter(getApplicationContext(), mlist);
        detilsPullto.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

        presenter.setVideoURl(pid);
        presenter.start();
    }

    @Override
    public void showAll(PandaCultureEntity entity) {

    }

    @Override
    public void showVideo(PandaCultureVedioBean pandaCultureVedioBean) {

        List<PandaCultureVedioBean.VideoBean.Chapters2Bean> chapters2 = pandaCultureVedioBean.getVideo().getChapters2();
        url = chapters2.get(0).getUrl();
        customVideoplayerStandardWithShareButton.setUrlAndObject(url, null, title);

    }

    @Override
    public void ShowTebie(PandaTebieBean tebieBean) {
        mlist.addAll(tebieBean.getVideo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setBasePresenter(CultureContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.collect_no, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect_no:

                    Toast.makeText(RollDtialActivity.this, "", Toast.LENGTH_SHORT).show();


                break;
            case R.id.share:
                break;
        }
    }
}