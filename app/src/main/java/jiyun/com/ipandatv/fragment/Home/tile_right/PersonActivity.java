package jiyun.com.ipandatv.fragment.Home.tile_right;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.LiveFragment;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;



import jiyun.com.ipandatv.R;




public class PersonActivity extends BaseFragment {
    @BindView(R.id.direct_tablayout)
    TabLayout directTablayout;
    @BindView(R.id.direct_viewpager)
    ViewPager directViewpager;
    private PandaDirectAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_person;
    }

    @Override
    protected void init(View view) {

    }



    @Override
    public void loadData() {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new LiveFragment());

        mListName.add("直播");
        mListName.add("精彩一刻");

        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        directViewpager.setAdapter(adapter);
        directTablayout.setupWithViewPager(directViewpager);

    }

    @Override
    public void setParams(Bundle bundle) {

    }
}
