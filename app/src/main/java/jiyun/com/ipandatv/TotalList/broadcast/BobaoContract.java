package jiyun.com.ipandatv.TotalList.broadcast;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.BobaoHeaderBean;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;

/**
 * Created by Lenovo on 2017/7/12.
 */

public interface BobaoContract {

    interface View extends BaseView<Presenter>{

        void showProgressDialog();
        void dismissDialog();
        void setResult(PandaBroadBean pandaLiveBean);
        void setResultHeadler(BobaoHeaderBean bobaoHeaderBean);
//        void loadMore(PandaBroadBean pandaLiveBean,int pagsize,int pagcontent);
//        void setRefresh(PandaBroadBean pandaLiveBean,int pagsize,int pagcontent);
        void showMessage(String msg);


    }

    interface Presenter extends BasePresenter{
    }
}
