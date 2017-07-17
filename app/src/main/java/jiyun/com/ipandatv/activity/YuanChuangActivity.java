package jiyun.com.ipandatv.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.TotalList.hudong.HudongContract;
import jiyun.com.ipandatv.TotalList.hudong.HudongPresenter;
import jiyun.com.ipandatv.adapter.HudongAdapter;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.model.entity.hudong.HudongBean;

/**
 * Created by Lenovo on 2017/7/17.
 */
public class YuanChuangActivity extends BaseActivity implements HudongContract.View {


    HudongContract.Presenter presenter;
    @BindView(R.id.Personal_Cente)
    ImageView PersonalCente;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Hudong_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;
    private HudongAdapter mAdapter;
    private List<HudongBean.InteractiveBean> mList = new ArrayList<>();
    private int Index = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.hudong_activity;
    }

    @Override
    protected void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(App.activity, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setPullRefreshEnabled(true);//下拉刷新
        //是否开启上拉加载功能
        mRecyclerView.setLoadingMoreEnabled(true);
        //开启刷新回调
        mRecyclerView.displayLastRefreshTime(true);
        //停止刷新
        //停止刷新
        mRecyclerView.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setRefreshComplete();
                        mList.clear();
                        loadData();

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.setLoadMoreComplete();
                        Index++;
                        loadData();

                    }
                }, 2000);
            }
        });


        mAdapter = new HudongAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void initData() {


    }

    @Override
    public void loadData() {

        HudongPresenter hudongPresenter = new HudongPresenter(this);
        presenter.start();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(HudongBean hudongBean) {
        mList.addAll(hudongBean.getInteractive());
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setBasePresenter(HudongContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Personal_Cente)
    public void onViewClicked() {

        onBackPressed();

    }


}
