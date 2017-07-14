package jiyun.com.ipandatv.adapter.homepage;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public class Home_viewpager_Adapter extends PagerAdapter {
    private ArrayList<View> list;

    public Home_viewpager_Adapter(ArrayList<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int newspostion =position%list.size();
        container.addView(list.get(newspostion));
        return list.get(newspostion);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
