package jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaCultureVedioBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.bean.PandaTebieBean;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;

/**
 * Created by INS7566 on 2017/7/13.
 */

public interface CultureContract {

    interface View extends BaseView <Presenter>{

        void showAll(PandaCultureEntity entity);
        //第一个条目的详情
        void showVideo(PandaCultureVedioBean pandaCultureVedioBean);
        void ShowTebie(PandaTebieBean tebieBean);

    }
    interface Presenter extends BasePresenter {
        void setVideoURl(String pid);
    }
}
