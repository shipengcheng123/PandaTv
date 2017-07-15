package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaNaxieshiBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaNaxieshiPresenter implements LiveTwoContract.Presenter {

    private LiveTwoContract.View liveFragment;
    private ILivemodel pandaLivemodel;

    public PandaNaxieshiPresenter(LiveTwoContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new LiveIpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getXMNXS(Urls.XMNXS, null, new INetWorkCallback<PandaNaxieshiBean>() {

            @Override
            public void OnSucess(PandaNaxieshiBean pandaNaxieshiBean) {
                liveFragment.showpandanaxieshiFragment(pandaNaxieshiBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
