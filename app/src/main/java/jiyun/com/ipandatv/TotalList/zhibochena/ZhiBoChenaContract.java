package jiyun.com.ipandatv.TotalList.zhibochena;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;

/**
 * Created by Lenovo on 2017/7/12.
 */

public interface ZhiBoChenaContract {

    interface View extends BaseView<Presenter>{
        void showProgressDialog();
        void dismissDialog();
        void getChinaLiveTab(PopupBean popupBean);
        void showMessage(String msg);

    }

    interface Presenter extends BasePresenter{

    }
}
