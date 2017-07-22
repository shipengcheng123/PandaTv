package jiyun.com.ipandatv.model.biz;

import jiyun.com.ipandatv.fragment.Home.bean.UpdateBean;
import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.urls.Urls;
import jiyun.com.ipandatv.model.entity.HomePageBean;
import jiyun.com.ipandatv.model.entity.PandaBean;

/**
 * Created by lx on 2017/7/11.
 * 业务接口实现类
 */

public class TotallabelModelimpl implements TotallabelModel {

    @Override
    public void getTotallabel(INetWorkCallback<PandaBean> callback) {
        iHttp.get(Urls.TOTALLABELURL, null, callback);
    }

    @Override
    public void getHomePageLunBo(INetWorkCallback<HomePageBean> callback) {
        iHttp.get(Urls.PANDATOTAL, null, callback);
    }
    @Override
    public void version(INetWorkCallback<UpdateBean> callBack) {
       HttpFactory.create().get(Urls.UPDATE_URL,null,callBack);
    }


}
