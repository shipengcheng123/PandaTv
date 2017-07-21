package jiyun.com.ipandatv.fragment.pandadirect;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;

/**
 * Created by INS7566 on 2017/7/12.
 */

public class LiveFragment extends BaseFragment implements LiveContract.View{

    @BindView(R.id.direct_shipin_linear)
    LinearLayout directShipinLinear;
    @BindView(R.id.direct_jianjie)
    TextView directJianjie;
    @BindView(R.id.live_isshow)
    CheckBox liveIsshow;
    @BindView(R.id.live_brief)
    TextView liveBrief;
    @BindView(R.id.live_back1)
    TextView liveBack1;
    @BindView(R.id.direct_framelayout)
    FrameLayout directFramelayout;
    @BindView(R.id.live_lin_brief)
    ScrollView liveLinBrief;
    @BindView(R.id.custom_videoplayer_standard_with_share_button)
    JCVideoPlayerStandard customVideoplayerStandardWithShareButton;
    private FragmentManager manager;

    boolean ischeck = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhibo;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {

        customVideoplayerStandardWithShareButton.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"你长得很爱国");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            manager = App.activity.getSupportFragmentManager();
        }
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.direct_framelayout, new PandaLivedsjtalkFragment());
        transaction.commit();
    }

    @Override
    public void setParams(Bundle bundle) {

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

    @OnClick(R.id.live_isshow)
    public void onViewClicked() {

        if (ischeck == false) {
            liveLinBrief.setVisibility(View.VISIBLE);
            ischeck = true;
            return;
        } else {
            liveLinBrief.setVisibility(View.GONE);
            ischeck = false;
            return;

        }
    }

    @Override
    public void showlivevedioFragment(PandaLiveBean pandaLiveBean) {

    }

    @Override
    public void showLiveFragment(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean) {

    }

    @Override
    public void showeyeFragment(PandaLiveTalkListBean pandaLiveTalkListBean) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setBasePresenter(LiveContract.Presenter presenter) {

    }
}
