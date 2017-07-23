package jiyun.com.ipandatv.internet;

/**
 * 工厂类 切换网络请求方式
 * Created by lx on 2017/7/11.
 */

public class HttpFactory {
    public static IHttp create() {
        return OkHttpUtils.getInstance();
    }
}
