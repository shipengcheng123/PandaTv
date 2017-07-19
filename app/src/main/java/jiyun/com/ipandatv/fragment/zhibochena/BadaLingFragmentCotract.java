package jiyun.com.ipandatv.fragment.zhibochena;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

/**
 * Created by Lenovo on 2017/7/18.
 */

public interface BadaLingFragmentCotract {
    interface  View extends BaseView<Presenter>{
        void getManager(ChangchengBean changchengBean);



    }
    interface Presenter extends BasePresenter{
        void setUrl(String url);
    }
}
