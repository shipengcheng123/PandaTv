package jiyun.com.ipandatv.activity;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandadirect.bean.VedioJCYKBean;

/**
 * Created by INS7566 on 2017/7/17.
 */

public interface VideoContract {
    interface View extends BaseView<Presenter> {
        //播放视频
        void showlivevedioFragment(VedioJCYKBean jcykBean);

    }

    interface Presenter extends BasePresenter {
       void setVideoURl(String pid);
    }
}
