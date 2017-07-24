package jiyun.com.ipandatv.fragment.pandabroadcast;

import android.app.ActionBar;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.fragment.pandabroadcast.adapter.PandaDetailAdapter;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaCultureVedioBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaTebieBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.CultureContract;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.PandaCultureVideoPresenter;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;

public class RollDtialActivity extends BaseActivity implements CultureContract.View, View.OnClickListener {

    @BindView(R.id.goback_butt)
    ImageView gobackButt;
    @BindView(R.id.custom_videoplayer_standard_with_share_button)
    JCVideoPlayerStandard customVideoplayerStandardWithShareButton;
    @BindView(R.id.rollvideo_time_text)
    TextView rollvideoTimeText;
    @BindView(R.id.rollvideo_jieshao_text)
    TextView rollvideoJieshaoText;

    @BindView(R.id.detiles_text)
    TextView detilesText;
    @BindView(R.id.visibility_linear)
    LinearLayout visibilityLinear;
//    @BindView(R.id.detils_pullto)
//    PullToRefreshRecyclerView detilsPullto;

    @BindView(R.id.collect_no)
    ImageView collectNo;
    @BindView(R.id.shares)
    ImageView shares;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.detils_pullto)
    XRecyclerView detilsPullto;
    List<PandaCultureVedioBean.VideoBean.Chapters2Bean> urllisst;
    @BindView(R.id.live_isshow)
    CheckBox liveIsshow;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    private CultureContract.Presenter presenter;
    private String pid, title;
    private PandaDetailAdapter adapter;
    private List<PandaTebieBean.VideoBean> mlist = new ArrayList<>();
    private int Index = 1;
    private String url;
    private PopupWindow PopupWindow;
    int pos = 0;
    boolean ischeck = false;

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
        urllisst = new ArrayList<>();

        //标准基础上改进的视频播放(添加了分享按钮)
        customVideoplayerStandardWithShareButton = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard_with_share_button);
        LinearLayoutManager layoutManager = new LinearLayoutManager(App.context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detilsPullto.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        detilsPullto.setLayoutManager(layoutManager);

//        detilsPullto.setPullRefreshEnabled(true);//下拉刷新
//        //是否开启上拉加载功能
//        detilsPullto.setLoadingMoreEnabled(true);
//        //开启刷新回调
//        detilsPullto.displayLastRefreshTime(true);
//        //停止刷新
//        //停止刷新
//        detilsPullto.setPullToRefreshListener(new PullToRefreshListener() {
//            @Override
//            public void onRefresh() {
//                detilsPullto.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        detilsPullto.setRefreshComplete();
//                        mlist.clear();
//                        loadData();
//
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onLoadMore() {
//                detilsPullto.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        detilsPullto.setLoadMoreComplete();
//                        Index++;
//                        loadData();
//
//                    }
//                }, 2000);
//            }
//        });
        adapter = new PandaDetailAdapter(mlist, this);
        detilsPullto.setAdapter(adapter);
        adapter.setOnClick(new PandaDetailAdapter.setOnClick() {
            @Override
            public void mSetOnClick(View v, int postion) {

                presenter.setVideoURl(mlist.get(postion).getVid());

                Toast.makeText(RollDtialActivity.this, "dsfsdfs", Toast.LENGTH_SHORT).show();
                pos = postion;

            }
        });
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
        if (urllisst.size() > 0) {

            urllisst.clear();
        }
        urllisst.addAll(chapters2);
        url = chapters2.get(0).getUrl();
        if (urllisst.size() > 0) {
            customVideoplayerStandardWithShareButton.setUp(urllisst.get(0).getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,title);
            customVideoplayerStandardWithShareButton.startVideo();
        }


    }

    @Override
    public void ShowTebie(PandaTebieBean tebieBean) {
        mlist.addAll(tebieBean.getVideo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String msg) {

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

    @OnClick({R.id.collect_no, R.id.shares, R.id.live_isshow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect_no:

                Toast.makeText(RollDtialActivity.this, "你已添加到我的收藏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.shares:
                showPopupWindow();
                break;
            case R.id.live_isshow:


            if (ischeck == false) {
                visibilityLinear.setVisibility(View.VISIBLE);
                ischeck = true;
                return;
            } else {
                visibilityLinear.setVisibility(View.GONE);
                ischeck = false;
                return;

            }


        }
    }

    public void showPopupWindow() {
        View mPopunwindwow = LayoutInflater.from(this).inflate(R.layout.popwindow_bottem, null);
        PopupWindow = new PopupWindow(mPopunwindwow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        PopupWindow.setContentView(mPopunwindwow);
        LinearLayout lineFacebook = (LinearLayout) mPopunwindwow.findViewById(R.id.facebook);
        LinearLayout linetwitter = (LinearLayout) mPopunwindwow.findViewById(R.id.twitter);
        LinearLayout lineweibo = (LinearLayout) mPopunwindwow.findViewById(R.id.weibo);
        LinearLayout lineweixin = (LinearLayout) mPopunwindwow.findViewById(R.id.weixin);
        LinearLayout linepengyouquan = (LinearLayout) mPopunwindwow.findViewById(R.id.pengyouquan);
        TextView quxiao = (TextView) mPopunwindwow.findViewById(R.id.quxiao);
        lineFacebook.setOnClickListener(this);
        linetwitter.setOnClickListener(this);
        lineweibo.setOnClickListener(this);
        lineweixin.setOnClickListener(this);
        linepengyouquan.setOnClickListener(this);
        quxiao.setOnClickListener(this);

        ColorDrawable dw = new ColorDrawable(this.getResources().getColor(R.color.colorWhite));
        PopupWindow.setBackgroundDrawable(dw);
        View rootview = LayoutInflater.from(this).inflate(R.layout.rollvideo_details, null);
        PopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebook:
                sharefacebook();
                break;
            case R.id.twitter:
                break;
            case R.id.weibo:
                weibofenxiang();
                break;
            case R.id.weixin:
                share();
                break;
            case R.id.pengyouquan:
                break;
            case R.id.quxiao:
                PopupWindow.dismiss();
                break;
        }

    }

    public void weibofenxiang() {
        UMImage image = new UMImage(RollDtialActivity.this, R.mipmap.xiongmao);//网络图片
        UMImage thumb = new UMImage(this, R.drawable.logo_ipnda);
        image.setThumb(thumb);

        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享

        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription("熊猫频道邀请你一同观看");//描述

        new ShareAction(RollDtialActivity.this)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withMedia(web)
                .setCallback(shareListener)//回调监听器
                .share();

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(RollDtialActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(RollDtialActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(RollDtialActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    public void share() {
        //构造一个Intent
        Intent intent = new Intent();
        //分享到微信好友
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareImgUI");
        //微信分享页面组件分享到朋友圈
        //  ComponentName comp = new ComponentName("com.tencent.mm",
        //         "ComponentName.comtencent.mm.ui.tools.Sion.SEND");
        intent.setType("image/*");//image上的所有格式hareToTimeLineUI");
        intent.setType("text/plain");
        intent.setComponent(comp);//这个方法与Intent进行通讯
        intent.setAction("android.intent.action.SEND");
        //intent.setFlags(0x3000001);
        intent.putExtra(Intent.EXTRA_STREAM, "熊猫频道");
        Bitmap bt = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.xiongmao);
        final Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bt, null, null));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }

    public void sharefacebook() {
        //构造一个Intent
        Intent intent = new Intent();
        //分享到微信好友
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareImgUI");
        //微信分享页面组件分享到朋友圈
        //  ComponentName comp = new ComponentName("com.tencent.mm",
        //         "ComponentName.comtencent.mm.ui.tools.Sion.SEND");
        intent.setType("image/*");//image上的所有格式hareToTimeLineUI");
        intent.setType("text/plain");
        intent.setComponent(comp);//这个方法与Intent进行通讯
        intent.setAction("android.intent.action.SEND");
        //intent.setFlags(0x3000001);
        intent.putExtra(Intent.EXTRA_SUBJECT, "熊猫频道");
        Bitmap bt = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.xiongmao);
        final Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bt, null, null));
        intent.putExtra(Intent.EXTRA_SUBJECT, uri);
        startActivity(intent);
    }

}