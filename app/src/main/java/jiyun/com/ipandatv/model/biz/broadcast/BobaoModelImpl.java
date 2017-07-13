package jiyun.com.ipandatv.model.biz.broadcast;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by Lenovo on 2017/7/13.
 */

public class BobaoModelImpl implements BobaoModel {
    @Override
    public void getPandaObserveHead(INetWorkCallback callback) {
        iHttp.get(Urls.PANDA_OBSERVE_HEAD, null, callback);
    }

    @Override
    public void getPandaObserveItem(INetWorkCallback callback) {
        iHttp.get(Urls.PANDA_OBSERVE_ITEM, null, callback);
    }
}
