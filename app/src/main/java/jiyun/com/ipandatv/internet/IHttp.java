package jiyun.com.ipandatv.internet;

import java.util.Map;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.callback.NNetWorkCallback;
import jiyun.com.ipandatv.internet.callback.NetWorkCallback;


/**
 * Created by lx on 2017/7/11.
 * 网络请求接口
 */

public interface IHttp {
    <T> void get(String url, Map<String, String> params, INetWorkCallback<T> callback);

    <T> void post(String url, Map<String, String> params, INetWorkCallback<T> callback);

    //登陆 图片验证码
    <T> void doget(String url, Map<String, String> params, NetWorkCallback callback);

    //获取手机验证码
    <T> void getSjYam(String url, Map<String, String> params, NNetWorkCallback callback);

    //手机号注册
    <T> void Register(String url, Map<String, String> params, NNetWorkCallback callback);

    <T> void EmailRegister(String url, Map<String, String> params, NNetWorkCallback callback);
}
