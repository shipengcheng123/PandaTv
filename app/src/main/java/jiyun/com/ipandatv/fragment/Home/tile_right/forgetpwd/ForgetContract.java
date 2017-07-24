package jiyun.com.ipandatv.fragment.Home.tile_right.forgetpwd;

import android.graphics.drawable.Drawable;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;

/**
 * Created by lx on 2017/7/24.
 */

public class ForgetContract {
    interface View extends BaseView<Presenter> {
        void setDrawable(Drawable drawable);

        void sendYam(String s);
    }

    interface Presenter extends BasePresenter {
        //获取手机验证码
        void getSjYam(String name, String TpYam);

        //找回密码
        void forgetPwd(String name, String Yzm, String pwd);
    }
}
