package jiyun.com.ipandatv.TotalList.popup;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;

/**
 * Created by Lenovo on 2017/7/18.
 */

public interface PopupContract {


    interface View extends BaseView<Presenter>{
        void showProgressDialog();
        void dismissDialog();
        void setChangcheng(PopupBean popupBean);

    }

    interface Presenter extends BasePresenter{

    }
}
