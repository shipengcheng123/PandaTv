package jiyun.com.ipandatv.fragment.Home.tile_right.register.email;

import android.graphics.drawable.Drawable;

import jiyun.com.ipandatv.internet.callback.NNetWorkCallback;
import jiyun.com.ipandatv.internet.callback.NetWorkCallback;
import jiyun.com.ipandatv.model.biz.TotallabelModel;
import jiyun.com.ipandatv.model.biz.TotallabelModelimpl;

/**
 * Created by lx on 2017/7/21.
 */

public class EmailRegisterPresenter implements EmailRegisterContract.Presenter {
    private TotallabelModel totallabelModel;
    private EmailRegisterContract.View homeView;

    public EmailRegisterPresenter(EmailRegisterContract.View homeView) {
        this.homeView = homeView;
        this.homeView.setBasePresenter(this);
        totallabelModel = new TotallabelModelimpl();
    }

    @Override
    public void Register(String name, String Yzm, String pwd) {
        totallabelModel.getEmailRegister(name, Yzm, pwd, new NNetWorkCallback() {
            @Override
            public void OnSuccess(String s) {
                homeView.set(s);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }

    @Override
    public void start() {
        totallabelModel.getPicture(new NetWorkCallback() {
            @Override
            public void OnSucess(Drawable drawable) {
                homeView.setDrawable(drawable);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
