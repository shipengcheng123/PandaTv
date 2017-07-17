package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/15.
 */

public class PandaDXBRPresenter implements LiveTwoContract.Presenter {
    private LiveTwoContract.View liveFragment;
    private ILivemodel pandaLivemodel;

    public PandaDXBRPresenter(LiveTwoContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new LiveIpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getDXBR(Urls.DXBR, null, new INetWorkCallback<PandaDangxiongburangBean>() {

            @Override
            public void OnSucess(PandaDangxiongburangBean pandaDangxiongburangBean) {
                liveFragment.showDXBRFragment(pandaDangxiongburangBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
