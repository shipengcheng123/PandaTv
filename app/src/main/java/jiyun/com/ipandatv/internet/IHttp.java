package jiyun.com.ipandatv.internet;

import java.util.Map;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;


/**
 * Created by lx on 2017/7/11.
 * 网络请求接口
 */

public interface IHttp {
    <T> void get(String url, Map<String, String> params, INetWorkCallback<T> callback);

    <T> void post(String url, Map<String, String> params, INetWorkCallback<T> callback);
}
