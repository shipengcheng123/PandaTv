package jiyun.com.ipandatv.model.biz.Zhibochena;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

import static jiyun.com.ipandatv.model.biz.BaseModel.iHttp;

/**
 * Created by Lenovo on 2017/7/14.
 */

public class zhiboChenaModelImpl implements ZhiboChenaModel{


    @Override
    public void getChangcheng(INetWorkCallback<ChangchengBean> callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Changcheng, null, callback);
    }

    @Override
    public void getHuangshan(INetWorkCallback<ChangchengBean> callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Huangshan, null, callback);
    }

    @Override
    public void getfenghuanggucheng(INetWorkCallback<ChangchengBean> callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Fenghuanggucheng, null, callback);
    }

    @Override
    public void getemeishan(INetWorkCallback<ChangchengBean> callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Emeishan, null, callback);
    }

    @Override
    public void getzhangjiajie(INetWorkCallback<ChangchengBean> callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Taishan, null, callback);
    }

    @Override
    public void getTaishan(INetWorkCallback callback) {
        iHttp.get(Urls.PANDA_LIVE_CHINA_Zhangjiajie, null, callback);
    }
}
