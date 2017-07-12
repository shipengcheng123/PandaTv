package jiyun.com.ipandatv.internet.callback;

/**
 * Created by lx on 2017/7/11.
 */

public interface INetWorkCallback<T> {
    void OnSucess(T t);

    void OnError(int ErrorCode, String ErrorMsg);
}
