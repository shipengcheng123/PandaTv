package jiyun.com.ipandatv.fragment.Home;

import com.nostra13.universalimageloader.utils.L;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.fragment.Home.bean.UpdateBean;
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

    @Override
    public void version() {
        totallabelModel.version(new INetWorkCallback<UpdateBean>() {
            @Override
            public void OnSucess(final UpdateBean updateBean) {
                //成功的回调
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        homeView.getVersion(updateBean);
                        L.d("版本更新bean打印",updateBean.getData().getVersionsinfo().toString());

                    }
                });
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
//                L.d("版本更新网络请求错误",errorMsg.toString());
            }

        });

    }
}