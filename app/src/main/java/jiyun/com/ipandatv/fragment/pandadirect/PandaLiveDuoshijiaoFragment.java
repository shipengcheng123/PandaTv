package jiyun.com.ipandatv.fragment.pandadirect;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaLiveDuoshijiaoAdapter;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.ptersenter.PandaLiveDuoshijiaoPresenter;


/**
 * Created by INS7566 on 2017/7/13.
 */

public class PandaLiveDuoshijiaoFragment extends BaseFragment implements LiveContract.View {
    @BindView(R.id.duoshijiaopullrecycler)
    PullToRefreshRecyclerView duoshijiaopullrecycler;
    Unbinder unbinder;
    private PandaLiveDuoshijiaoAdapter adapter;
    private List<PandaLiveDuoshijiaoBean.ListBean> mList=new ArrayList<>();
    private LiveContract.Presenter presenter;
    private int Index=1;

    private PandaLiveDuoshijiaoPresenter presente;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_duoshijiao;
    }

    @Override
    protected void init(View view) {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        duoshijiaopullrecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        duoshijiaopullrecycler.setLayoutManager(gridLayoutManager);
        duoshijiaopullrecycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        duoshijiaopullrecycler.setLoadingMoreEnabled(true);
        //开启刷新回调
        duoshijiaopullrecycler.displayLastRefreshTime(true);
        //停止刷新
        //停止刷新
        duoshijiaopullrecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                duoshijiaopullrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        duoshijiaopullrecycler.setRefreshComplete();
                        mList.clear();
                        loadData();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                duoshijiaopullrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        duoshijiaopullrecycler.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });

       adapter = new PandaLiveDuoshijiaoAdapter(getContext(),mList);
        duoshijiaopullrecycler.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

        presente=new PandaLiveDuoshijiaoPresenter(this);
        presenter.start();
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

    @Override
    public void showLiveFragment(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean) {
        mList.addAll(pandaLiveDuoshijiaoBean.getList());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showeyeFragment(PandaLiveTalkListBean pandaLiveTalkListBean) {

    }

    @Override
    public void setBasePresenter(LiveContract.Presenter presenter) {
this.presenter=presenter;
    }
}
