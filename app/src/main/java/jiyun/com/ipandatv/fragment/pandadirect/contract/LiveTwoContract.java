package jiyun.com.ipandatv.fragment.pandadirect.contract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaChaomenggunxiuBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDanganBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJcyiBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaNaxieshiBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaTOPBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaTeBiejimuBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaYuanchuangxinwenBean;

/**
 * Created by INS7566 on 2017/7/14.
 */

public interface LiveTwoContract {
    interface View extends BaseView<LiveTwoContract.Presenter> {

        //精彩一刻
        void showjcyiFragment(PandaLiveJcyiBean pandaLiveJcyiBean);
        //当熊不让

        //超萌滚滚秀
        void showchaomenggunxiuFrangment(PandaChaomenggunxiuBean pandaChaomenggunxiuBean);
        //熊猫档案
        void showxiongmaoDanganFragment(PandaDanganBean pandaDanganBean);
        //熊猫Top榜
        void showpandaTOP(PandaTOPBean pandaTOPBean);
        //熊猫那些事
        void showpandanaxieshiFragment(PandaNaxieshiBean pandaNaxieshiBean);
        //特别节目
        void showpanTebiejiemuFragment(PandaTeBiejimuBean pandaTeBiejimuBean);
        //原创新闻
        void showyuanchuangxinwenFragment(PandaYuanchuangxinwenBean pandaYuanchuangxinwenBean);

    }

    interface Presenter extends BasePresenter {

    }
}
