package jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaCultureVedioBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaTebieBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.IPandaCulture;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureImp;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/18.
 */

public class PandaCultureVideoPresenter implements CultureContract.Presenter {

    private CultureContract.View pandaculture;
    private IPandaCulture pandaCulture;

    public PandaCultureVideoPresenter(CultureContract.View view) {
        this.pandaculture=view;
        this.pandaculture.setBasePresenter(this);
        pandaCulture = new PandaCultureImp();

    }

    @Override
    public void start() {
        pandaCulture.getculuretebie(Urls.VEDIOCULTURE,null, new INetWorkCallback<PandaTebieBean>() {
            @Override
            public void OnSucess(PandaTebieBean tebieBean) {
                pandaculture.ShowTebie(tebieBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });

    }

    @Override
    public void setVideoURl(String pid) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        pandaCulture.getPandaCultureVideo(map, new INetWorkCallback<PandaCultureVedioBean>() {
            @Override
            public void OnSucess(PandaCultureVedioBean pandaCultureVedioBean) {
                pandaculture.showVideo(pandaCultureVedioBean);
                Log.d("bbbbbbb",pandaCultureVedioBean.toString());
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
