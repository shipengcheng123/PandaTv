package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJCYKBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.PandaLiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.IPandaLivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.PandaLiveImpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by INS7566 on 2017/7/24.
 */

public class PandaLivePresenter implements PandaLiveContract.Presenter {
    private PandaLiveContract.View view;
    private IPandaLivemodel pandaLivemodel;
    public PandaLivePresenter(PandaLiveContract.View view){
        this.view=view;
        this.view.setBasePresenter(this);
        pandaLivemodel=new PandaLiveImpl();
    }
    @Override
    public void start() {
        pandaLivemodel.liveFragment(new INetWorkCallback<PandaLiveJCYKBean>() {
            @Override
            public void OnSucess(PandaLiveJCYKBean pandaLiveJCYKBean) {
                view.showLiveFragment(pandaLiveJCYKBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
