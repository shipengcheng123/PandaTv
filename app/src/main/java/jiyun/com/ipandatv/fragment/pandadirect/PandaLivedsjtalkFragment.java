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
import butterknife.Unbinder;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;

/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaLivedsjtalkFragment extends BaseFragment {
    @BindView(R.id.direct_tablayout)
    TabLayout directTablayout;
    @BindView(R.id.direct_viewpager)
    ViewPager directViewpager;
    Unbinder unbinder;
    private PandaDirectAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dsjtalk;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new PandaLiveDuoshijiaoFragment());
        mList.add(new PandaLiveDuoshijiaoFragment());

        mListName.add("多视角直播");
        mListName.add("边看边聊");

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
