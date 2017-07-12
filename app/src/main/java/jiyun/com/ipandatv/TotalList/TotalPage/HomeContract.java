package jiyun.com.ipandatv.TotalList.TotalPage;


import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;

/**
 * Created by lx on 2017/7/11.
 * 契约类
 * 将两者进行契约
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void setText(PandaBean pandaBean);

        void showmsg(String errormsg);
    }

    interface Presenter extends BasePresenter {

    }
}
