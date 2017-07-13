package jiyun.com.ipandatv.fragment.Home;


import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/11.
 * 契约类
 * 将两者进行契约
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void setImage(HomePageBean homePageBean);

        void setText(HomePageBean homePageBean);

        void setMsg(String msg);
    }

    interface Presenter extends BasePresenter {

    }
}
