package jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract;

import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.IPandaCulture;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureImp;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaCulturePresenter implements CultureContract.Presenter {

private CultureContract.View pandaculture;
    private IPandaCulture pandaCulture;

    public PandaCulturePresenter(CultureContract.View view) {
        this.pandaculture=view;
        this.pandaculture.setBasePresenter(this);
        pandaCulture = new PandaCultureImp();

    }

    @Override
    public void start() {
        pandaCulture.getPandaCultureHead(Urls.PANDACULTURE, new INetWorkCallback<PandaCultureEntity>() {

            @Override
            public void OnSucess(PandaCultureEntity pandaCultureEntity) {
                pandaculture.showAll(pandaCultureEntity);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {
                pandaculture.showMessage(ErrorMsg);
            }
        });
    }

    @Override
    public void setVideoURl(String pid) {

    }
}
