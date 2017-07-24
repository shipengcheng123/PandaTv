package jiyun.com.ipandatv.fragment.pandadirect.fragment;

import android.os.Bundle;
import android.os.Handler;
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
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PabdaDXBRAdapter;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaDangxiongburangBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveDuoshijiaoBean;
import jiyun.com.ipandatv.fragment.pandadirect.bean.PandaLiveTalkListBean;
import jiyun.com.ipandatv.fragment.pandadirect.contract.LiveContract;
import jiyun.com.ipandatv.fragment.pandadirect.ptersenter.PandaLiveJCYKPresenter;
import jiyun.com.ipandatv.utils.ShowPopuUtils;

/**
 * Created by INS7566 on 2017/7/24.
 */

public class PandaJCYKFragment extends BaseFragment implements LiveContract.View{
    @BindView(R.id.jcyk_pullrecycler)
    PullToRefreshRecyclerView jcykPullrecycler;
    Unbinder unbinder;
    private PabdaDXBRAdapter adapter;
    private List<PandaDangxiongburangBean.VideoBean> mList=new ArrayList<>();
    private LiveContract.Presenter presenter;
    private int Index=1;

    private Handler handleProgress = new Handler();
//    private ProgressDialog progressDialog = null;
    private Bundle bundle=null;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_jcyk;
    }

    @Override
    protected void init(View view) {
        ShowPopuUtils.getInsent().create(App.activity);

        PandaLiveJCYKPresenter pandaLiveJCYKPresenter=new PandaLiveJCYKPresenter(this);
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
                }, 200);
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
                }, 200);
            }
        });



    }

    @Override
    protected void loadData() {

        presenter.start();

        adapter = new PabdaDXBRAdapter(getContext(),mList);
        jcykPullrecycler.setAdapter(adapter);

        if(bundle!=null) {
            String vid = bundle.getString("vid");
            presenter.setVidManager(vid);
        }


//        handleProgress.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    @Override
    public void setParams(Bundle bundle) {
        this.bundle=bundle;
    }


    @Override
    public void showlivevedioFragment(PandaLiveBean pandaLiveBean) {

    }

    @Override
    public void showLiveFragment(PandaLiveDuoshijiaoBean pandaLiveDuoshijiaoBean) {

    }

    @Override
    public void showeyeFragment(PandaLiveTalkListBean pandaLiveTalkListBean) {

    }



    @Override
    public void showJcykFragment(PandaDangxiongburangBean pandaDangxiongburangBean) {
        mList.addAll(pandaDangxiongburangBean.getVideo());
        adapter.notifyDataSetChanged();
        ShowPopuUtils.getInsent().popuUtilsDismiss();
//        progressDialog.dismiss();
    }
    @Override
    public void showMessage(String msg) {
        ACache aCache = ACache.get(getContext());
        PandaDangxiongburangBean pandaChaomenggunxiuObject =
                (PandaDangxiongburangBean) aCache.
                        getAsObject("PandaDangxiongburangBean");
        mList.addAll(pandaChaomenggunxiuObject.getVideo());
        adapter.notifyDataSetChanged();


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
    public void setBasePresenter(LiveContract.Presenter presenter) {
        this.presenter=presenter;
    }
}
