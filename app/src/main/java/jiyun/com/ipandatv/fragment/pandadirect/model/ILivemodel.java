package jiyun.com.ipandatv.fragment.pandadirect.model;

import java.util.Map;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by INS7566 on 2017/7/14.
 */

public interface ILivemodel {
    //vsid=VSET100272959126     &n=7      &serviceId=panda              &  o=desc       &of=time       &p=1
   <T> void getJCYI(String url, Map<String, String> params, INetWorkCallback<T> callback);
//    <T> void getJCYI(String vsid,String n,String serviceId,String o,String of,String p,  INetWorkCallback<T> callback);
    <T> void getDXBR(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getCMGGX(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMDA(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMTOP(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getXMNXS(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getTBJM(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getYCXW(String url, Map<String, String> params, INetWorkCallback<T> callback);
    <T> void getVDJCYK(Map<String,String> params,INetWorkCallback<T> callback);
    <T> void getTitle(Map<String,String> params,INetWorkCallback<T> callback);
}
