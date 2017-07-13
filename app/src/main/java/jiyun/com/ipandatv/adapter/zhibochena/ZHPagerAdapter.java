package jiyun.com.ipandatv.adapter.zhibochena;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jiyun.com.ipandatv.base.BaseFragment;

/**
 * Created by lx on 2017/5/9.
 */

public class ZHPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> data;
    private List<String> titleList;
    public ZHPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> data, List<String> titleList) {
        super(fm);
        this.data = data;
        this.titleList = titleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }


}
