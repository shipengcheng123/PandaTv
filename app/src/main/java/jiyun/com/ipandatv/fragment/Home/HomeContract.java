package jiyun.com.ipandatv.fragment.Home;


import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.Home.bean.UpdateBean;
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
        void setMessage(String msg);


        //获取版本号
        void getVersion(UpdateBean updateBean);
    }

    interface Presenter extends BasePresenter {
        void version();

    }
}
