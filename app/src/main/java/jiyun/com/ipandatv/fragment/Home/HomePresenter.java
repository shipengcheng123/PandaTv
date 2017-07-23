package jiyun.com.ipandatv.fragment.Home;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.TotallabelModel;
import jiyun.com.ipandatv.model.biz.TotallabelModelimpl;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/11.
 * 实现契约中的presenter方法
 * 网络请求
 */

public class HomePresenter implements HomeContract.Presenter {
    private TotallabelModel totallabelModel;
    private HomeContract.View homeView;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        this.homeView.setBasePresenter(this);
        totallabelModel = new TotallabelModelimpl();
    }

    @Override
    public void start() {
        totallabelModel.getHomePageLunBo(new INetWorkCallback<HomePageBean>() {
            @Override
            public void OnSucess(HomePageBean homePageBean) {
                homeView.setText(homePageBean);
                homeView.setImage(homePageBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                homeView.setMsg(ErrorMsg);
                homeView.setMessage(ErrorMsg);
            }
        });
    }
}