package jiyun.com.ipandatv.model.biz.Zhibochena;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;

/**
 * Created by Lenovo on 2017/7/14.
 */

public interface ZhiboChenaModel {

    void getChangcheng(INetWorkCallback<ChangchengBean> callback);
    void getTaishan(INetWorkCallback<ChangchengBean> callback);
    void getHuangshan(INetWorkCallback<ChangchengBean> callback);
    void getfenghuanggucheng(INetWorkCallback<ChangchengBean> callback);
    void getemeishan(INetWorkCallback<ChangchengBean> callback);
    void getzhangjiajie(INetWorkCallback<ChangchengBean> callback);
    void getLiveChinaUrl(String url,INetWorkCallback<ChangchengBean> callback);
    void getLiveChinaTab(INetWorkCallback<PopupBean> callback);
}
