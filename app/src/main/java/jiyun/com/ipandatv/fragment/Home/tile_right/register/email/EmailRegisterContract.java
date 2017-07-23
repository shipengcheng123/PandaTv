package jiyun.com.ipandatv.fragment.Home.tile_right.register.email;

import android.graphics.drawable.Drawable;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;

/**
 * Created by lx on 2017/7/21.
 */

public class EmailRegisterContract {
    interface View extends BaseView<Presenter> {
        void setDrawable(Drawable drawable);

        void set(String s);
    }

    interface Presenter extends BasePresenter {

        void Register(String name, String Yzm, String pwd);
    }
}
