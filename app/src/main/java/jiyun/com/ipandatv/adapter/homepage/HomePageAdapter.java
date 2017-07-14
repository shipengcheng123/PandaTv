package jiyun.com.ipandatv.adapter.homepage;


import android.content.Context;

import com.androidkun.adapter.BaseAdapter;
import com.androidkun.adapter.ViewHolder;

import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/12.
 */

public class HomePageAdapter extends BaseAdapter<HomePageBean> {

    public HomePageAdapter(Context context, List<HomePageBean> datas) {
        super(context, R.layout.layout_load_more_view, datas);
    }

    @Override
    public void convert(ViewHolder holder, HomePageBean homePageBean) {

    }
}
