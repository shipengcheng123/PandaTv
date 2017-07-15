package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaYuanchuangxinwenBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaYuanchuangxinwemPresenter implements LiveTwoContract.Presenter {

    private LiveTwoContract.View liveFragment;
    private ILivemodel pandaLivemodel;

    public PandaYuanchuangxinwemPresenter(LiveTwoContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new LiveIpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getYCXW(Urls.YCXW, null, new INetWorkCallback<PandaYuanchuangxinwenBean>() {

            @Override
            public void OnSucess(PandaYuanchuangxinwenBean pandaYuanchuangxinwenBean) {
                liveFragment.showyuanchuangxinwenFragment(pandaYuanchuangxinwenBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }

}
