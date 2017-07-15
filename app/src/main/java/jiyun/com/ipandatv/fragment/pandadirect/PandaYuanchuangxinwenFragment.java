package jiyun.com.ipandatv.fragment.pandadirect;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaYuanchuangxinwenAdapter;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaChaomenggunxiuBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDanganBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveJcyiBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaNaxieshiBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaTOPBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaTeBiejimuBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaYuanchuangxinwenBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveTwoContract;
import jiyun.com.ipandatv.fragment.pandadirect.ptersenter.PandaYuanchuangxinwemPresenter;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaYuanchuangxinwenFragment extends BaseFragment implements LiveTwoContract.View{
    @BindView(R.id.jcyk_pullrecycler)
    PullToRefreshRecyclerView jcykPullrecycler;
    Unbinder unbinder;
    private PandaYuanchuangxinwenAdapter adapter;
    private List<PandaYuanchuangxinwenBean.VideoBean> mList=new ArrayList<>();
    private LiveTwoContract.Presenter presenter;
    private int Index=1;
    private PandaYuanchuangxinwemPresenter presente;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jcyk;
    }

    @Override
    protected void init(View view) {
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jcykPullrecycler.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        jcykPullrecycler.setLayoutManager(layoutManager);
        jcykPullrecycler.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        jcykPullrecycler.setLoadingMoreEnabled(true);
        //开启刷新回调
        jcykPullrecycler.displayLastRefreshTime(true);
        //停止刷新
        //停止刷新
        jcykPullrecycler.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                jcykPullrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jcykPullrecycler.setRefreshComplete();
                        mList.clear();
                        loadData();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                jcykPullrecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        jcykPullrecycler.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });

        adapter = new PandaYuanchuangxinwenAdapter(getContext(),mList);
        jcykPullrecycler.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        presente=new PandaYuanchuangxinwemPresenter(this);
        presenter.start();
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void showjcyiFragment(PandaLiveJcyiBean pandaLiveJcyiBean) {

    }

    @Override
    public void showchaomenggunxiuFrangment(PandaChaomenggunxiuBean pandaChaomenggunxiuBean) {

    }

    @Override
    public void showxiongmaoDanganFragment(PandaDanganBean pandaDanganBean) {

    }

    @Override
    public void showpandaTOP(PandaTOPBean pandaTOPBean) {

    }

    @Override
    public void showpandanaxieshiFragment(PandaNaxieshiBean pandaNaxieshiBean) {

    }

    @Override
    public void showpanTebiejiemuFragment(PandaTeBiejimuBean pandaTeBiejimuBean) {

    }

    @Override
    public void showyuanchuangxinwenFragment(PandaYuanchuangxinwenBean pandaYuanchuangxinwenBean) {
        mList.addAll(pandaYuanchuangxinwenBean.getVideo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setBasePresenter(LiveTwoContract.Presenter presenter) {
        this.presenter=presenter;
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
