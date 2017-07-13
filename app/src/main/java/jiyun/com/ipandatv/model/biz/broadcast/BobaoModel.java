package jiyun.com.ipandatv.model.biz.broadcast;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.BaseModel;

/**
 * Created by Lenovo on 2017/7/13.
 */

public interface BobaoModel extends BaseModel {

    void getPandaObserveHead(INetWorkCallback callback);
    void getPandaObserveItem(INetWorkCallback callback);

}
