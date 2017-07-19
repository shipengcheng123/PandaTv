package jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract;

import jiyun.com.ipandatv.base.BasePresenter;
import jiyun.com.ipandatv.base.BaseView;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;

/**
 * Created by INS7566 on 2017/7/13.
 */

public interface CultureContract {

    interface View extends BaseView {


        void showAll(PandaCultureEntity entity);

    }
    interface Presenter extends BasePresenter {

    }
}
