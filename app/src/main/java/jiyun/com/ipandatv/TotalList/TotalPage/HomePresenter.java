package jiyun.com.ipandatv.TotalList.TotalPage;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.TotallabelModel;
import jiyun.com.ipandatv.model.biz.TotallabelModelimpl;
import jiyun.com.ipandatv.model.entity.PandaBean;

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
        totallabelModel.getTotallabel(new INetWorkCallback<PandaBean>() {
            @Override
            public void OnSucess(PandaBean pandaBean) {
                homeView.setText(pandaBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                homeView.showmsg(ErrorMsg);
            }
        });
    }
}