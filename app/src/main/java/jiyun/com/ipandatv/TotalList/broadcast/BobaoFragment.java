package jiyun.com.ipandatv.TotalList.broadcast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.androidkun.callback.PullToRefreshListener;
import com.bumptech.glide.Glide;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.activity.WebActivity;
import jiyun.com.ipandatv.adapter.BobaoAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;
import jiyun.com.ipandatv.model.db.JiluDao;
import jiyun.com.ipandatv.model.db.MyOpenHelper;
import jiyun.com.ipandatv.model.entity.BobaoHeaderBean;
import jiyun.com.ipandatv.model.entity.PandaBroadBean;
import jiyun.com.ipandatv.utils.MyLog;
import jiyun.com.ipandatv.utils.ShowPopuUtils;

/**
 * Created by Lenovo on 2017/7/12.
 */

public class BobaoFragment extends BaseFragment implements BobaoContract.View {


    @BindView(R.id.bobao_RecyclerView)
    PullToRefreshRecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.Personal_Cente)
    ImageView PersonalCente;
    private ImageView mImage;
    private TextView title;
    private View view1;
    BobaoContract.Presenter presenter;
    private List<PandaBroadBean.ListBean> mList = new ArrayList<>();
    private BobaoAdapter bobaoAdapter;
    private String urls;
    private BobaoHeaderBean bobaoHeaderBean;
    private int Index = 1;
    private BobaoPresenter bobaoPresenter;
    private String url;
    private Handler handleProgress = new Handler();
    private ProgressDialog progressDialog = null;
    private Dao<JiluDao,Integer> dao;
    private String mTitle,tupian;
    private boolean flag=false;
    @Override
    protected int getLayoutId() {
        return R.layout.bobao_fragment;
    }

    @Override
    protected void init(View view) {
        ShowPopuUtils.getInsent().create(App.activity);
        MyOpenHelper helper = new MyOpenHelper(getContext(), "guankanjilu.db", null, 1);

        try {
            dao = helper.getDao(JiluDao.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        view1 = LayoutInflater.from(getContext()).inflate(R.layout.image_header_fragment, null);
        mImage = (ImageView) view1.findViewById(R.id.Header_image);
        title = (TextView) view1.findViewById(R.id.Header_title);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", url);
                MyLog.e("URL", url);
                startActivity(intent);

                try {
                    List<JiluDao> chaxunItem = dao.queryForAll();
                    if(chaxunItem.size() == 0) {
                        JiluDao jiluDao = new JiluDao();
                        jiluDao.setTitle(mTitle);
                        jiluDao.setImageurl(tupian);
                        int i = dao.create(jiluDao);
                        Log.e("AAA", "插入了" + i + "条数据");
                    }
                    else {
                        for (int i=0;i<chaxunItem.size();i++){
                            if(mTitle.equals(chaxunItem.get(i).getTitle())) {
                                flag=true;
                                return;
                            }
                        }
                        if(flag) {
                            Log.e("tag","相同");
                        }
                        else {
                            JiluDao jiluDao = new JiluDao();
                            jiluDao.setTitle(mTitle);
                            jiluDao.setImageurl(tupian);
                            int i = dao.create(jiluDao);
                            Log.e("AAA", "插入了" + i + "条数据");
                            Log.e("tag","添加");
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }



            }
        });

        mRecyclerView.addHeaderView(view1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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





    }

    @Override
    protected void loadData() {
        bobaoPresenter = new BobaoPresenter(this);
        presenter.start();
        bobaoAdapter = new BobaoAdapter(getContext(), mList);
        mRecyclerView.setAdapter(bobaoAdapter);


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

    @OnClick(R.id.Personal_Cente)
    public void onViewClicked() {

        Intent in = new Intent(getContext(), Title_RightActivity.class);
        startActivity(in);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void setResult(PandaBroadBean pandaLiveBean) {

        mList.addAll(pandaLiveBean.getList());
        bobaoAdapter.notifyDataSetChanged();
        ShowPopuUtils.getInsent().popuUtilsDismiss();
    }
    @Override
    public void showMessage(String msg) {
        ACache aCache = ACache.get(getContext());
        PandaBroadBean pandaBroadBean = (PandaBroadBean) aCache.getAsObject("PandaBroadBean");
        mList.addAll(pandaBroadBean.getList());
        bobaoAdapter.notifyDataSetChanged();
        ShowPopuUtils.getInsent().popuUtilsDismiss();
    }


    @Override
    public void setResultHeadler(BobaoHeaderBean bobaoHeaderBean) {
         tupian = bobaoHeaderBean.getData().getBigImg().get(0).getImage();
        Glide.with(App.activity).load(tupian).into(mImage);
        mTitle = bobaoHeaderBean.getData().getBigImg().get(0).getTitle();
        this.title.setText(mTitle);
        url = bobaoHeaderBean.getData().getBigImg().get(0).getUrl();

    }
    @Override
    public void ShowMessageTwo(String msg) {
        ACache aCache = ACache.get(getContext());
        BobaoHeaderBean asObject = (BobaoHeaderBean) aCache.getAsObject("BobaoHeaderBean");
        Glide.with(App.activity).load(asObject.getData().getBigImg().get(0).getImage()).into(mImage);
        mTitle = asObject.getData().getBigImg().get(0).getTitle();
        title.setText(mTitle);
        ShowPopuUtils.getInsent().popuUtilsDismiss();
    }




    @Override
    public void setBasePresenter(BobaoContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
