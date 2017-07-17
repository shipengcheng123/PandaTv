package jiyun.com.ipandatv.fragment.pandadirect.model;

import java.util.Map;

import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaLiveImpl implements IPandaLivemodel {
    //获得熊猫直播标题
    @Override
    public <T> void getTabTitle(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(url,params,callback);
    }

    //获得
    @Override
    public <T> void getData(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    //显示图片
    @Override
    public <T> void showImage(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    @Override
    public <T> void gettitle(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    //获取详细信息
    @Override
    public <T> void getDetailInfo(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    //多视角直播标题

    //边看边聊标题
    @Override
    public <T> void getEyeTitle(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    //多视角直播
    @Override
    public <T> void getLiveFragment(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.DUOSHIJIAO,params,callback);
    }

    //边看表聊
    @Override
    public <T> void getEyeFragment(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.TALKLIST,params,callback);
    }

    @Override
    public <T> void getJcyk(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    @Override
    public <T> void getTalkList(String url, Map<String, String> params, INetWorkCallback<T> callback) {

        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

    @Override
    public <T> void vedioPlay(String url, Map<String, String> params, INetWorkCallback<T> callback) {
        HttpFactory.create().get(Urls.PANDALIVE,params,callback);
    }

}
