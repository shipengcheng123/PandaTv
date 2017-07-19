package jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture;

import java.util.Map;

import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by hp1 on 2017-04-07.
 */

public class PandaCultureImp implements IPandaCulture {
    @Override
    public void getPandaCultureHead(String url, INetWorkCallback myCallback) {
        HttpFactory.create().get(url,null,myCallback);
    }

    @Override
    public void getPandaCultureItem(String url, INetWorkCallback myCallback) {
        HttpFactory.create().get(url,null,myCallback);
    }

    @Override
    public <T> void getPandaCultureVideo(Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.VEDIOJCYK,params,callback);
    }

    @Override
    public <T> void getculuretebie(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.VEDIOCULTURE,params,callback);
    }

}
