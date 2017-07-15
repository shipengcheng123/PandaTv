package jiyun.com.ipandatv.TotalList.zhibochena;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

/**
 * Created by Lenovo on 2017/7/12.
 */

public interface ZhiBoChenaContract {

    interface View extends BaseView<Presenter>{
        void showProgressDialog();
        void dismissDialog();
        void setChangcheng(ChangchengBean changchengBean);
        void setTaishan(ChangchengBean changchengBean);
        void setHuangshan(ChangchengBean changchengBean);
        void setfenghuanggucheng(ChangchengBean changchengBean);
        void setemeishan(ChangchengBean changchengBean);
        void setzhangjiajie(ChangchengBean changchengBean);

        void showMessage(String msg);

    }

    interface Presenter extends BasePresenter{

    }
}
