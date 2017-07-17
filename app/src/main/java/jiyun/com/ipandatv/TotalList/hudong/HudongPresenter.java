package jiyun.com.ipandatv.TotalList.hudong;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.hudong.HudongModel;
import jiyun.com.ipandatv.model.biz.hudong.HudongModelImpl;
import jiyun.com.ipandatv.model.entity.hudong.HudongBean;

/**
 * Created by Lenovo on 2017/7/17.
 */

public class HudongPresenter implements HudongContract.Presenter{

    private HudongModel hudongModel;
    private HudongContract.View Hudongview;


    public HudongPresenter(HudongContract.View Hudongview){
        this.Hudongview = Hudongview;
        this.Hudongview.setBasePresenter(this);
        hudongModel = new HudongModelImpl();
    }


    @Override
    public void start() {
        hudongModel.getHudong(new INetWorkCallback<HudongBean>() {
            @Override
            public void OnSucess(HudongBean hudongBean) {
                Hudongview.setResult(hudongBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
