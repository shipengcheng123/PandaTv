package jiyun.com.ipandatv.adapter.homepage;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lx on 2017/7/13.
 * 轮播图适配器
 */

public class HomeViewPagerAdapter extends PagerAdapter {
    private List<View> list;

    public HomeViewPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size() > 0 ? Integer.MAX_VALUE : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (list.size() > 0)
            container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (container != null) {
            container.removeView(list.get(position % list.size()));
        }
        if (list.size() > 0)
            container.addView(list.get(position % list.size()));
        return list.get(position % list.size());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
