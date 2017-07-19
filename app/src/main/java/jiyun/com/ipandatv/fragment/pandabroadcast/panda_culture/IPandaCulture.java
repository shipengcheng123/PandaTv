package jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture;

import java.util.Map;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.BaseModel;

/**
 * Created by hp1 on 2017-04-07.
 */

public interface IPandaCulture extends BaseModel {
    void getPandaCultureHead(String url, INetWorkCallback myCallback);
    void getPandaCultureItem(String url, INetWorkCallback myCallback);

    <T> void getPandaCultureVideo(Map<String,String> params, INetWorkCallback<T> callback);
    <T> void getculuretebie(String url, Map<String, String> params, INetWorkCallback<T> callback);
}
