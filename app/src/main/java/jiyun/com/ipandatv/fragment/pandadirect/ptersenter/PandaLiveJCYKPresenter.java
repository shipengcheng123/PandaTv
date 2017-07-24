package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.IPandaLivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.PandaLiveImpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by INS7566 on 2017/7/24.
 */

public class PandaLiveJCYKPresenter implements LiveContract.Presenter {
    private LiveContract.View liveFragment;
    private IPandaLivemodel pandaLivemodel;

    public PandaLiveJCYKPresenter(LiveContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new PandaLiveImpl();

    }
    @Override
    public void start() {

    }

    @Override
    public void setVidManager(String vsid) {
        pandaLivemodel.livejcyk(vsid, new INetWorkCallback<PandaDangxiongburangBean>() {

            @Override
            public void OnSucess(PandaDangxiongburangBean pandaDangxiongburangBean) {
                liveFragment.showJcykFragment(pandaDangxiongburangBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                liveFragment.showMessage(ErrorMsg);
            }
        });
    }
}
