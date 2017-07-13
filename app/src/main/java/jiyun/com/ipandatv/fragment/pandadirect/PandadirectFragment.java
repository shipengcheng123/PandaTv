package jiyun.com.ipandatv.fragment.pandadirect;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;


/**
 * Created by INS7566 on 2017/7/12.
 */

public class PandadirectFragment extends BaseFragment {
    @BindView(R.id.direct_tablayout)
    TabLayout directTablayout;
    @BindView(R.id.direct_viewpager)
    ViewPager directViewpager;
    private PandaDirectAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pandadirect;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mList.add(new LiveFragment());
        mListName.add("直播");
        mListName.add("精彩一刻");
        mListName.add("当熊不让");
        mListName.add("超萌滚滚秀");
        mListName.add("熊猫档案");
        mListName.add("熊猫TOP榜");
        mListName.add("熊猫那些事儿");
        mListName.add("特别节目");
        mListName.add("原始新闻");

        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        directViewpager.setAdapter(adapter);
        directTablayout.setupWithViewPager(directViewpager);

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
