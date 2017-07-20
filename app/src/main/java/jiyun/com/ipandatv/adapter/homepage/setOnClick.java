package jiyun.com.ipandatv.adapter.homepage;

import android.view.View;

import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/20.
 */

public interface setOnClick {
    void setOnClick(HomePageBean.DataBean.PandaeyeBean homePageBean, int position, View view);
    void setOnClick(HomePageBean.DataBean.PandaliveBean pandaliveBean,int position,View view);
}
