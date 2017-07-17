package jiyun.com.ipandatv.model.biz.hudong;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;
import jiyun.com.ipandatv.model.entity.hudong.HudongBean;

import static jiyun.com.ipandatv.model.biz.BaseModel.iHttp;

/**
 * Created by Lenovo on 2017/7/17.
 */

public class HudongModelImpl implements HudongModel {
    @Override
    public void getHudong(INetWorkCallback<HudongBean> callback) {
        iHttp.get(Urls.INTERACTURL, null, callback);
    }
}
