package jiyun.com.ipandatv.fragment.pandadirect.model;

import java.util.Map;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by INS7566 on 2017/7/14.
 */

public interface ILivemodel {
    <T> void getJCYI(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getDXBR(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getCMGGX(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMDA(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMTOP(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMNXS(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getTBJM(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getYCXW(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getVDJCYK(Map<String,String> params,INetWorkCallback<T> callback);

}
