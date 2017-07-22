package jiyun.com.ipandatv.fragment.Home.tile_right.login;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.TotallabelModel;
import jiyun.com.ipandatv.model.biz.TotallabelModelimpl;
import jiyun.com.ipandatv.model.entity.LoginBean;

/**
 * Created by lx on 2017/7/21.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private TotallabelModel totallabelModel;
    private LoginContract.View LoginView;

    public LoginPresenter(LoginContract.View LoginView) {
        this.LoginView = LoginView;
        this.LoginView.setBasePresenter(this);
        totallabelModel = new TotallabelModelimpl();
    }

    @Override
    public void start() {

    }

    @Override
    public void Login(String name, String pwd) {
        totallabelModel.Login(name, pwd, new INetWorkCallback<LoginBean>() {
            @Override
            public void OnSucess(LoginBean loginBean) {
                LoginView.LoginOclick(loginBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
