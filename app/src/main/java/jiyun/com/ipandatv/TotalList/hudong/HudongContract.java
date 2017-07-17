package jiyun.com.ipandatv.TotalList.hudong;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.hudong.HudongBean;

/**
 * Created by Lenovo on 2017/7/17.
 */

public interface HudongContract {

     interface View extends BaseView<Presenter> {
         void showProgressDialog();

         void dismissDialog();

         void setResult(HudongBean hudongBean);

         void showMessage(String msg);
     }

    interface Presenter extends BasePresenter{

    }

}
