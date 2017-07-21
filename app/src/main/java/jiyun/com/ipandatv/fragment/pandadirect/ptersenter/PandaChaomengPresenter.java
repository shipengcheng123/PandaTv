package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaChaomenggunxiuBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaChaomengPresenter implements LiveTwoContract.Presenter {
    private LiveTwoContract.View liveFragment;
    private ILivemodel pandaLivemodel;

    public PandaChaomengPresenter(LiveTwoContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new LiveIpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getCMGGX(Urls.CMGGX, null, new INetWorkCallback<PandaChaomenggunxiuBean>() {

            @Override
            public void OnSucess(PandaChaomenggunxiuBean pandaChaomenggunxiuBean) {
                liveFragment.showchaomenggunxiuFrangment(pandaChaomenggunxiuBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                liveFragment.showMessage(ErrorMsg);
            }
        });
    }
}
