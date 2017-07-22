package jiyun.com.ipandatv.fragment.Home.tile_right.login;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.LoginBean;

/**
 * Created by lx on 2017/7/21.
 */

public class LoginContract {
    interface View extends BaseView<Presenter> {
        void LoginOclick(LoginBean loginBean);

    }

    interface Presenter extends BasePresenter {
        void Login(String name, String pwd);
    }
}
