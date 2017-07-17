package jiyun.com.ipandatv.fragment.pandadirect.model;

import java.util.Map;

import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class LiveIpl implements ILivemodel{
    @Override
    public <T> void getJCYI(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.JCYI,params,callback);
    }

    @Override
    public <T> void getDXBR(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.DXBR,params,callback);
    }

    @Override
    public <T> void getCMGGX(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.CMGGX,params,callback);
    }

    @Override
    public <T> void getXMDA(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.XMDA,params,callback);
    }

    @Override
    public <T> void getXMTOP(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.XMTop,params,callback);
    }

    @Override
    public <T> void getXMNXS(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.XMNXS,params,callback);
    }

    @Override
    public <T> void getTBJM(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.TBJM,params,callback);
    }

    @Override
    public <T> void getYCXW(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.YCXW,params,callback);
    }

    @Override
    public <T> void getVDJCYK( Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.VEDIOJCYK,params,callback);
    }
}
