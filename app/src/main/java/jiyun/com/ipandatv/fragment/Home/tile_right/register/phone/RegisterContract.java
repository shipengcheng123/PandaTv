package jiyun.com.ipandatv.fragment.Home.tile_right.register.phone;

import android.graphics.drawable.Drawable;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;

/**
 * Created by lx on 2017/7/21.
 */

public class RegisterContract {
    interface View extends BaseView<Presenter> {
        void setDrawable(Drawable drawable);

        void sendYam(String s);
    }

    interface Presenter extends BasePresenter {
        void getSjYam(String name, String TpYam);

        void Register(String name, String Yzm, String pwd);
    }
}
