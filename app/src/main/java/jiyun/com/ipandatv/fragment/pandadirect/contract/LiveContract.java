package jiyun.com.ipandatv.fragment.pandadirect.contract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;

/**
 * Created by INS7566 on 2017/7/12.
 */

public interface LiveContract {

    interface View extends BaseView<Presenter> {
        //播放视频
        void showlivevedioFragment(PandaLiveBean pandaLiveBean);
        //多视角直播
        void showLiveFragment(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean);
        //边看边聊
        void showeyeFragment(PandaLiveTalkListBean pandaLiveTalkListBean);

        void showMessage(String msg);

        void showJcykFragment(PandaDangxiongburangBean pandaDangxiongburangBean);
    }

    interface Presenter extends BasePresenter {
        void setVidManager(String vsid);
    }
}
