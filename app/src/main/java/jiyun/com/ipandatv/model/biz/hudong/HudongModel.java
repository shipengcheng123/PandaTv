package jiyun.com.ipandatv.model.biz.hudong;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.entity.hudong.HudongBean;

/**
 * Created by Lenovo on 2017/7/17.
 */

public interface HudongModel {

    void getHudong(INetWorkCallback<HudongBean> callback);

}
