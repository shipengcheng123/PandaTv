package jiyun.com.ipandatv.fragment.pandadirect.contract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaChaomenggunxiuBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJcyiBean;

/**
 * Created by INS7566 on 2017/7/14.
 */

public interface LiveTwoContract {
    interface View extends BaseView<LiveTwoContract.Presenter> {

        //精彩一刻
        void showjcyiFragment(PandaLiveJcyiBean pandaLiveJcyiBean);
        //当熊不让
        void showDXBRFragment(PandaDangxiongburangBean pandaDangxiongburangBean);
        //超萌滚滚秀
        void showchaomenggunxiuFrangment(PandaChaomenggunxiuBean pandaChaomenggunxiuBean);

        void showMessage(String msg);
    }

    interface Presenter extends BasePresenter {

    }
}
