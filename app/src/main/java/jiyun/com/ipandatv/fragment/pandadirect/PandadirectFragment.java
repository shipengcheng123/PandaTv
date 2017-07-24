package jiyun.com.ipandatv.fragment.pandadirect;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJCYKBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.PandaLiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.ptersenter.PandaLivePresenter;


/**
 * Created by INS7566 on 2017/7/12.
 */

public class PandadirectFragment extends BaseFragment implements PandaLiveContract.View{


    @BindView(R.id.direct_tablayout)
    TabLayout directTablayout;
    @BindView(R.id.direct_viewpager)
    ViewPager directViewpager;
    @BindView(R.id.direct_login_iv)
    ImageView directLoginIv;
    private PandaDirectAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;
    private PandaLivePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pandadirect;
    }

    @Override
    protected void init(View view) {
        new PandaLivePresenter(this);
        presenter.start();
    }

    @Override
    protected void loadData() {


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

    @OnClick(R.id.direct_login_iv)
    public void onViewClicked() {
        Intent intent=new Intent(App.activity.getApplication(), Title_RightActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLiveFragment(PandaLiveJCYKBean pandaLiveJCYKBean) {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        List<PandaLiveJCYKBean.TablistBean> tablist = pandaLiveJCYKBean.getTablist();
        jiyun.com.ipandatv.fragment.pandadirect.fragment.PandaJCYKFragment pandaJCYKFragment=null;
        Bundle bundle=null;

        mListName.add("直播");
        mList.add(new LiveFragment());

        for (int i=1;i<tablist.size();i++){
            PandaLiveJCYKBean.TablistBean tablistBean = tablist.get(i);
            pandaJCYKFragment=new jiyun.com.ipandatv.fragment.pandadirect.fragment.PandaJCYKFragment();
            String title = tablistBean.getTitle();
            bundle = new Bundle();
            bundle.putString("vid",tablistBean.getId());
            pandaJCYKFragment.setParams(bundle);
            mListName.add(title);
            mList.add(pandaJCYKFragment);
        }

        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        directViewpager.setAdapter(adapter);
        directTablayout.setupWithViewPager(directViewpager);
    }

    @Override
    public void setBasePresenter(PandaLiveContract.Presenter presenter) {
        this.presenter= (PandaLivePresenter) presenter;
    }
}
