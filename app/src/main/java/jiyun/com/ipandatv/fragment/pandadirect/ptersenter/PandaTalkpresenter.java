package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.IPandaLivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.PandaLiveImpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;
import jiyun.com.ipandatv.utils.MyLog;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaTalkpresenter implements LiveContract.Presenter {
    private LiveContract.View liveFragment;
    private IPandaLivemodel pandaLivemodel;

    public PandaTalkpresenter(LiveContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new PandaLiveImpl();
    }
    @Override
    public void start() {
        pandaLivemodel.getEyeFragment(Urls.TALKLIST, null, new INetWorkCallback<PandaLiveTalkListBean>() {

            @Override
            public void OnSucess(PandaLiveTalkListBean pandaLiveTalkListBean) {
                liveFragment.showeyeFragment(pandaLiveTalkListBean);
                MyLog.e("talk",pandaLiveTalkListBean.getData().getTotal());
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                liveFragment.showMessage(ErrorMsg);
            }
        });
    }

    @Override
    public void setVidManager(String vsid) {

    }
}
