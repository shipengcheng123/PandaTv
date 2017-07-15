package jiyun.com.ipandatv.TotalList.zhibochena;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.Zhibochena.ZhiboChenaModel;
import jiyun.com.ipandatv.model.biz.Zhibochena.zhiboChenaModelImpl;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

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

        zhiboChenaModel.getChangcheng(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
               ZhiboView.setChangcheng(changchengBean);
     }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });

        zhiboChenaModel.getTaishan(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                ZhiboView.setTaishan(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
        zhiboChenaModel.getHuangshan(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                ZhiboView.setHuangshan(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
        zhiboChenaModel.getfenghuanggucheng(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                ZhiboView.setfenghuanggucheng(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });

        zhiboChenaModel.getemeishan(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                ZhiboView.setemeishan(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
        zhiboChenaModel.getzhangjiajie(new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                ZhiboView.setzhangjiajie(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });

    }
}
