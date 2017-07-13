package jiyun.com.ipandatv.model.biz;

import jiyun.com.ipandatv.model.entity.HomePageBean;
import jiyun.com.ipandatv.model.entity.PandaBean;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by lx on 2017/7/11.
 * 处理业务逻辑（数据读写）
 */

public interface TotallabelModel extends BaseModel {
    void getTotallabel(INetWorkCallback<PandaBean> callback);

    //首页轮播图接口
    void getHomePageLunBo(INetWorkCallback<HomePageBean> callback);
}
