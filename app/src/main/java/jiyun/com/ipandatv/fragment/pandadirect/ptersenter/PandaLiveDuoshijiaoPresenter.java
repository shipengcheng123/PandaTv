package jiyun.com.ipandatv.fragment.pandadirect.ptersenter;

import java.util.List;

import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.model.IPandaLivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.PandaLiveImpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;


/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaLiveDuoshijiaoPresenter implements LiveContract.Presenter {

//
    private List<PandaLiveBean.BookmarkBean.MultipleBean> multipleBeen;
    private List<PandaLiveBean.BookmarkBean.WatchTalkBean> watchTalkBeen;
    private LiveContract.View liveFragment;
    private IPandaLivemodel pandaLivemodel;

    public PandaLiveDuoshijiaoPresenter(LiveContract.View view) {
        this.liveFragment=view;
        this.liveFragment.setBasePresenter(this);
        pandaLivemodel = new PandaLiveImpl();

    }
    @Override
    public void start() {
        pandaLivemodel.getLiveFragment(Urls.PANDALIVE, null, new INetWorkCallback<PandaLiveDuoshijiaoBean>() {

            @Override
            public void OnSucess(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean) {
                liveFragment.showLiveFragment(pandaLiveDuoshijiaoBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
