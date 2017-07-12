package jiyun.com.ipandatv.model.biz;

import jiyun.com.ipandatv.model.entity.PandaBean;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;

/**
 * Created by lx on 2017/7/11.
 * 业务接口实现类
 */

public class TotallabelModelimpl implements TotallabelModel {

    @Override
    public void getTotallabel(INetWorkCallback<PandaBean> callback) {
        iHttp.get(Urls.TOTALLABELURL, null, callback);
    }
}
