package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.IPandaLivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.PandaLiveImpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/17.
 */

public class PandaLiveVedioPresenter implements LiveContract.Presenter {
    private LiveContract.View liveFragment;
    private IPandaLivemodel pandaLivemodel;

    public PandaLiveVedioPresenter(LiveContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new PandaLiveImpl();

    }
    @Override
    public void start() {
        pandaLivemodel.vedioPlay(Urls.PANDALIVE, null, new INetWorkCallback<PandaLiveBean>() {

            @Override
            public void OnSucess(PandaLiveBean pandaLiveBean) {
                liveFragment.showlivevedioFragment(pandaLiveBean);
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
