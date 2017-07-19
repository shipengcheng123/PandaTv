package jiyun.com.ipandatv.fragment.pandabroadcast.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import jiyun.com.ipandatv.adapter.homepage.setViewPagerListener;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaCultureBannerAdapter extends PagerAdapter {
    List<ImageView> list;
    setViewPagerListener listner;
    public PandaCultureBannerAdapter(List<ImageView> list) {
        this.list = list;
    }
    public void setViewPagerListner(setViewPagerListener listner) {
        this.listner = listner;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = list.get(position % list.size());
        if (imageView.getParent() != null) {
            ((ViewGroup) imageView.getParent()).removeView(imageView);
        }
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() > 0)
                    listner.setViewPager(position % list.size());
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //    super.destroyItem(container, position, object);
    }
}
