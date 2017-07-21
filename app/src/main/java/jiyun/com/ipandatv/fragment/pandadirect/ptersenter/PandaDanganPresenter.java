package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDanganBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaDanganPresenter implements LiveTwoContract.Presenter {
    private LiveTwoContract.View liveFragment;
    private ILivemodel pandaLivemodel;

    public PandaDanganPresenter(LiveTwoContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new LiveIpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getXMDA(Urls.XMDA, null, new INetWorkCallback<PandaDanganBean>() {

            @Override
            public void OnSucess(PandaDanganBean pandaDanganBean) {
                liveFragment.showxiongmaoDanganFragment(pandaDanganBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                liveFragment.showMessage(ErrorMsg);
            }
        });
    }
}
