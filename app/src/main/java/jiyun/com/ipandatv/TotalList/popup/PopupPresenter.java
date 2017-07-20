package jiyun.com.ipandatv.TotalList.popup;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.Zhibochena.ZhiboChenaModel;
import jiyun.com.ipandatv.model.biz.Zhibochena.zhiboChenaModelImpl;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;

/**
 * Created by Lenovo on 2017/7/18.
 */

public class PopupPresenter implements PopupContract.Presenter {

    private ZhiboChenaModel zhiboChenaModel;
    private PopupContract.View popupview;

    public PopupPresenter(PopupContract.View popupview) {
        this.popupview = popupview;
        this.popupview.setBasePresenter(this);
        zhiboChenaModel = new zhiboChenaModelImpl();
    }


    @Override
    public void start() {

    zhiboChenaModel.getLiveChinaTab(new INetWorkCallback<PopupBean>() {
        @Override
        public void OnSucess(PopupBean popupBean) {
            popupview.setChangcheng(popupBean);
        }

        @Override
        public void OnError(int ErrorCode, String ErrorMsg) {

        }
    });
    }
}
