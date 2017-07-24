package jiyun.com.ipandatv.fragment.pandadirect.contract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJCYKBean;

/**
 * Created by INS7566 on 2017/7/24.
 */

public interface PandaLiveContract {
    interface View extends BaseView<Presenter>{
        void showLiveFragment(PandaLiveJCYKBean pandaLiveJCYKBean);
    }
    interface Presenter extends BasePresenter{

    }
}
