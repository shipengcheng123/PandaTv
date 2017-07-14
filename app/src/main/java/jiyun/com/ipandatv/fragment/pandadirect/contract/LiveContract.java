package jiyun.com.ipandatv.fragment.pandadirect.contract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;

/**
 * Created by INS7566 on 2017/7/12.
 */

public interface LiveContract {

    interface View extends BaseView<Presenter> {

        /**
         * 多视角直播
         */
        void showLiveFragment(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean);
        //边看边聊
        void showeyeFragment(PandaLiveTalkListBean pandaLiveTalkListBean);

    }

    interface Presenter extends BasePresenter {

    }
}
