package jiyun.com.ipandatv.fragment.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.activity.WebActivity;
import jiyun.com.ipandatv.activity.YuanChuangActivity;
import jiyun.com.ipandatv.adapter.homepage.HomeViewPagerAdapter;
import jiyun.com.ipandatv.adapter.homepage.Home_Adapter;
import jiyun.com.ipandatv.adapter.homepage.setViewPagerListener;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/11.
 * 实现契约中的view方法
 * 拿到从presenter层网络请求出来的东西并放上去
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, View.OnClickListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.title_center)
    TextView titleCenter;
    @BindView(R.id.title_inter)
    TextView titleInter;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.PulltoRefresh)
    PullToRefreshRecyclerView PulltoRefresh;
    private HomeContract.Presenter presenter;
    private Home_Adapter home_adapter;
    private List<Object> mList;
    private List<View> Pagerview = new ArrayList<>();
    private ViewPager mViewPager;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private LinearLayout linearLayout;
    private int currmentNum = 100000;
    private View v, v1;


    @Override
    protected int getLayoutId() {
        return R.layout.main_headpage;
    }

    @Override
    protected void init(View view) {
        v = LayoutInflater.from(App.activity).inflate(R.layout.home_viewpager_main, null);
        linearLayout = (LinearLayout) v.findViewById(R.id.home_viewpager_linearLayout);
        mViewPager = (ViewPager) v.findViewById(R.id.home_viewpager);
        App.mRadiogroup.setVisibility(View.VISIBLE);
        titleRight.setOnClickListener(this);
        titleInter.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(App.activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        PulltoRefresh.setLayoutManager(linearLayoutManager);
        PulltoRefresh.addHeaderView(v);
        mViewPager.setFocusable(true);
        mViewPager.setFocusableInTouchMode(true);
        mViewPager.requestFocus();
    }

    @Override
    protected void loadData() {
        App.mRadiogroup.setVisibility(View.VISIBLE);
//        progressDialog = ProgressDialog.show(App.activity,"请稍等...","获取数据中...",true);
        new HomePresenter(this);
        presenter.start();

    }

    @Override
    public void setParams(Bundle bundle) {
    }

    @Override
    public void setImage(HomePageBean homePageBean) {
        List<HomePageBean.DataBean.BigImgBean> bigImgBeanList = homePageBean.getData().getBigImg();
        showLunBo(bigImgBeanList);
    }

    @Override
    public void setText(HomePageBean homePageBean) {
        mList = new ArrayList<>();
        HomePageBean.DataBean data = homePageBean.getData();
        mList.add(data.getPandaeye());
        mList.add(data.getArea());
        mList.add(data.getChinalive());
        mList.add(data.getWalllive());
        mList.add(data.getPandalive());
        home_adapter = new Home_Adapter(App.activity, mList);
        PulltoRefresh.setAdapter(home_adapter);
        home_adapter.notifyDataSetChanged();
    }

    @Override
    public void setMsg(String msg) {
        ACache aCache = ACache.get(getContext());
        HomePageBean asObject = (HomePageBean) aCache.getAsObject("HomePageBean");
        List<HomePageBean.DataBean.BigImgBean> bigImgBeanList = asObject.getData().getBigImg();
        showLunBo(bigImgBeanList);
    }

    @Override
    public void setMessage(String msg) {
        ACache aCache = ACache.get(getContext());
        HomePageBean homePageobject = (HomePageBean) aCache.getAsObject("HomePageBean");
        mList = new ArrayList<>();
        HomePageBean.DataBean data = homePageobject.getData();
        mList.add(data.getPandaeye());
        mList.add(data.getArea());
        mList.add(data.getChinalive());
        mList.add(data.getWalllive());
        mList.add(data.getPandalive());
        home_adapter = new Home_Adapter(App.activity, mList);
        PulltoRefresh.setAdapter(home_adapter);
        home_adapter.notifyDataSetChanged();
    }

    @Override
    public void setBasePresenter(HomeContract.Presenter presenter) {
//        Log.i("setBasePresenter", "执行了setBasePresenter方法");
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
                Intent intent = new Intent(App.activity, YuanChuangActivity.class);
                startActivity(intent);
                break;
            case R.id.title_right:
                Intent in = new Intent(App.activity, Title_RightActivity.class);
                startActivity(in);
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currmentNum = position;
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (i == currmentNum % checkBoxes.size()) {
                checkBoxes.get(i).setChecked(true);
            } else {
                checkBoxes.get(i).setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //轮播图
    private void showLunBo(final List<HomePageBean.DataBean.BigImgBean> bigImgBeen) {
        v = null;
        CheckBox checkBox;
        v1 = null;
        for (HomePageBean.DataBean.BigImgBean bigImgBean : bigImgBeen) {
            v1 = LayoutInflater.from(App.activity).inflate(R.layout.checkbox_item, null);
            checkBox = (CheckBox) v1.findViewById(R.id.viewpager_checkbox_btn);
            linearLayout.addView(v1);
            checkBoxes.add(checkBox);
            v = LayoutInflater.from(App.activity).inflate(R.layout.image_header_fragment, null);
            ImageView imageView = (ImageView) v.findViewById(R.id.Header_image);
            TextView title = (TextView) v.findViewById(R.id.Header_title);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String image = bigImgBean.getImage();
            String titlestr = bigImgBean.getTitle();
            Glide.with(App.activity).load(image).into(imageView);
            title.setText(titlestr);
            Pagerview.add(v);
        }

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(Pagerview);
        mViewPager.setAdapter(adapter);
        checkBoxes.get(currmentNum % checkBoxes.size()).setChecked(true);
        mViewPager.setCurrentItem(currmentNum);
        handler.sendEmptyMessageDelayed(222, 2000);
        adapter.setViewPagerListner(new setViewPagerListener() {
            @Override
            public void setViewPager(int position) {
                HomePageBean.DataBean.BigImgBean bigImgBean = bigImgBeen.get(position);
                if (position == 0) {
                    String url = bigImgBean.getUrl();
                    String title = bigImgBean.getTitle();
                    Intent in = new Intent(App.activity, WebActivity.class);
                    in.putExtra("url", url);
                    in.putExtra("title", title);
                    startActivity(in);
                } else {
                    String pid = bigImgBean.getPid();
                    String title = bigImgBean.getTitle();
                    Intent in = new Intent(App.activity, VideoActivity.class);
                    in.putExtra("pid", pid);
                    in.putExtra("title", title);
                    startActivity(in);
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 222:
                    currmentNum++;
                    mViewPager.setCurrentItem(currmentNum);
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        if (i == currmentNum % checkBoxes.size()) {
                            checkBoxes.get(i).setChecked(true);
                        } else {
                            checkBoxes.get(i).setChecked(false);
                        }
                    }
                    handler.sendEmptyMessageDelayed(222, 2000);
                    break;
            }
        }
    };

}