package jiyun.com.ipandatv.activity;

import java.util.HashMap;
import java.util.Map;

import jiyun.com.ipandatv.fragment.pandadirect.bean.VedioJCYKBean;
import jiyun.com.ipandatv.fragment.pandadirect.model.ILivemodel;
import jiyun.com.ipandatv.fragment.pandadirect.model.LiveIpl;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by INS7566 on 2017/7/17.
 */

public class VideoPresenter implements VideoContract.Presenter{
    private VideoContract.View view;
    private ILivemodel livemodel;
    public VideoPresenter(VideoContract.View view){
        this.view=view;
        view.setBasePresenter(this);
        this.livemodel=new LiveIpl();
    }
    @Override
    public void setVideoURl(String pid) {
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        livemodel.getVDJCYK(map, new INetWorkCallback<VedioJCYKBean>() {
            @Override
            public void OnSucess(VedioJCYKBean jcykBean) {
                view.showlivevedioFragment(jcykBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }

    @Override
    public void start() {

    }
}
