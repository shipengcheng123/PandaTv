package jiyun.com.ipandatv.fragment.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.homepage.HomeXrecyclerAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.config.ConfigFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_Right;
import jiyun.com.ipandatv.model.entity.HomePageBean;

import static jiyun.com.ipandatv.R.id.title_right;

/**
 * Created by lx on 2017/7/11.
 * 实现契约中的view方法
 * 拿到从presenter层网络请求出来的东西并放上去
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener {
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_center)
    TextView titleCenter;
    @BindView(R.id.title_inter)
    TextView titleInter;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(title_right)
    TextView titleRight;
    Unbinder unbinder;
    private HomeContract.Presenter presenter;
    XRecyclerView home_xrecyclerview;
    HomeXrecyclerAdapter homeXrecyclerAdapter;
    private ArrayList<HomePageBean> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.main_headpage;
    }


    @Override
    protected void init(View view) {
        App.mRadiogroup.setVisibility(View.VISIBLE);
        home_xrecyclerview = (XRecyclerView) view.findViewById(R.id.home_xrecyclerview);
        titleRight.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        App.mRadiogroup.setVisibility(View.VISIBLE);
        new HomePresenter(this);
        presenter.start();
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void setImage(HomePageBean homePageBean) {
    }

    @Override
    public void setText(HomePageBean homePageBean) {
        list.add(homePageBean);
        home_xrecyclerview.setLayoutManager(new LinearLayoutManager(App.context));
        home_xrecyclerview.setLoadingMoreProgressStyle(R.drawable.loading_10);
        homeXrecyclerAdapter = new HomeXrecyclerAdapter(list, getContext());
        home_xrecyclerview.setAdapter(homeXrecyclerAdapter);
        home_xrecyclerview.setLoadingMoreEnabled(true);
        home_xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                home_xrecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                home_xrecyclerview.loadMoreComplete();
            }
        });
    }

    @Override
    public void setMsg(String msg) {

    }

    @Override
    public void setBasePresenter(HomeContract.Presenter presenter) {
        Log.i("setBasePresenter", "执行了setBasePresenter方法");
        this.presenter = presenter;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_inter:

                break;
            case R.id.title_right:
                ConfigFragment.getInstance().init().start(Title_Right.class).build();
                break;
        }
    }

    @Override
    protected void onShow() {
        super.onShow();
        App.mRadiogroup.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onHidden() {
        super.onHidden();
    }
}