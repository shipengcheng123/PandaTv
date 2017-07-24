package jiyun.com.ipandatv.TotalList.broadcast;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.broadcast.BobaoModel;
import jiyun.com.ipandatv.model.biz.broadcast.BobaoModelImpl;
import jiyun.com.ipandatv.model.entity.BobaoHeaderBean;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;

/**
 * Created by Lenovo on 2017/7/12.
 */

public class BobaoPresenter implements BobaoContract.Presenter {

    private BobaoModel bobaoModel;
    private BobaoContract.View BobaoView;
    
    public BobaoPresenter(BobaoContract.View BobaoView) {
        this.BobaoView = BobaoView;
        this.BobaoView.setBasePresenter(this);
        bobaoModel = new BobaoModelImpl();
    }

    @Override
    public void start() {
        bobaoModel.getPandaObserveItem(new INetWorkCallback<PandaBroadBean>() {
            @Override
            public void OnSucess(PandaBroadBean pandaBroadBean) {
                BobaoView.setResult(pandaBroadBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

                BobaoView.showMessage(ErrorMsg);
            }
        });
        bobaoModel.getPandaObserveHead(new INetWorkCallback<BobaoHeaderBean>() {
            @Override
            public void OnSucess(BobaoHeaderBean bobaoHeaderBean) {
                BobaoView.setResultHeadler(bobaoHeaderBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                BobaoView.ShowMessageTwo(ErrorMsg);
            }
        });
    }
}
