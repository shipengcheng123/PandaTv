package jiyun.com.ipandatv.TotalList.zhibochena;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.Zhibochena.ZhiboChenaModel;
import jiyun.com.ipandatv.model.biz.Zhibochena.zhiboChenaModelImpl;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;

/**
 * Created by Lenovo on 2017/7/12.
 */

public class ZhiBoChenaPresenter implements ZhiBoChenaContract.Presenter {

    private ZhiboChenaModel zhiboChenaModel;
    private ZhiBoChenaContract.View ZhiboView;

    public ZhiBoChenaPresenter(ZhiBoChenaContract.View ZhiboView) {
        this.ZhiboView = ZhiboView;
        this.ZhiboView.setBasePresenter(this);
        zhiboChenaModel = new zhiboChenaModelImpl();
    }


    @Override
    public void start() {

        zhiboChenaModel.getLiveChinaTab(new INetWorkCallback<PopupBean>() {
            @Override
            public void OnSucess(PopupBean popupBean) {
                ZhiboView.getChinaLiveTab(popupBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });

    }
}
