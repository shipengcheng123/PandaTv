package jiyun.com.ipandatv.fragment.Home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.homepage.HomePageAdapter;
import jiyun.com.ipandatv.adapter.homepage.HomePageViewAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.model.entity.HomePageBean;

/**
 * Created by lx on 2017/7/11.
 * 实现契约中的view方法
 * 拿到从presenter层网络请求出来的东西并放上去
 */

public class HomeFragment extends BaseFragment implements HomeContract.View, ViewPager.OnPageChangeListener {
    private PullToRefreshRecyclerView HomePageRecycler;
    private HomeContract.Presenter presenter;
    private ViewPager mLunBo;
    private List<HomePageBean> mList = new ArrayList<>();
    private HomePageAdapter homePageAdapter;
    private View LunBo;
    //轮播
    private int currentItem = 1000000;
    private final int CODE_start = 1;
    private final int CODE_END = 2;
    private HomePageViewAdapter MyAdapter;
    private List<View> viewList;
    private RadioButton mRadioBut1, mRadioBut2, mRadioBut3, mRadioBut4;
    private ImageView mImage1, mImage2, mImage3, mImage4;
    private TextView mLunBoTitle1, mLunBoTitle2, mLunBoTitle3, mLunBoTitle4;
    //轮播发送
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_start:
                    mLunBo.setCurrentItem(currentItem++);
                    handler.sendEmptyMessageDelayed(CODE_start, 2000);
                    break;
                case CODE_END:
                    handler.removeMessages(CODE_start);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.main_headpage;
    }

    @Override
    protected void init(View view) {
        HomePageRecycler = (PullToRefreshRecyclerView) view.findViewById(R.id.HomePage_Recycler);
        Log.i("init", "执行了init方法");
        LunBo = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.main_header, null);
        mLunBo = (ViewPager) LunBo.findViewById(R.id.lunbo);
        mLunBo.setOnPageChangeListener(this);
        mRadioBut1 = (RadioButton) LunBo.findViewById(R.id.RadioBut1);
        mRadioBut2 = (RadioButton) LunBo.findViewById(R.id.RadioBut2);
        mRadioBut3 = (RadioButton) LunBo.findViewById(R.id.RadioBut3);
        mRadioBut4 = (RadioButton) LunBo.findViewById(R.id.RadioBut4);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        HomePageRecycler.setLayoutManager(manager);
        viewList = new ArrayList<>();
        MyAdapter = new HomePageViewAdapter(viewList);
        handler.sendEmptyMessageDelayed(CODE_start, 2000);
    }

    @Override
    protected void loadData() {
        lunBo();
        Log.i("loadData", "执行了loadData方法");
        homePageAdapter = new HomePageAdapter(getContext(), mList);
        HomePageRecycler.setAdapter(homePageAdapter);
        HomePageRecycler.getHeaderViews();
        HomePageRecycler.addHeaderView(LunBo);
        homePageAdapter.notifyDataSetChanged();
        presenter.start();
    }


    //轮播
    private void lunBo() {
        View view = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.shouye_lunbo1, null);
        View view1 = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.shouye_lunbo2, null);
        View view2 = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.shouye_lunbo3, null);
        View view3 = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.shouye_lunbo4, null);
        mImage1 = (ImageView) view.findViewById(R.id.Image1);
        mImage2 = (ImageView) view1.findViewById(R.id.Image2);
        mImage3 = (ImageView) view2.findViewById(R.id.Image3);
        mImage4 = (ImageView) view3.findViewById(R.id.Image4);
        mLunBoTitle1 = (TextView) view.findViewById(R.id.LunBo_Title1);
        mLunBoTitle2 = (TextView) view1.findViewById(R.id.LunBo_Title2);
        mLunBoTitle3 = (TextView) view2.findViewById(R.id.LunBo_Title3);
        mLunBoTitle4 = (TextView) view3.findViewById(R.id.LunBo_Title4);
        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        mLunBo.setAdapter(MyAdapter);
        mLunBo.setCurrentItem(currentItem++);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        currentItem = position;
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        switch (position % viewList.size()) {
            case 0:
                mRadioBut1.setChecked(true);
                break;
            case 1:
                mRadioBut2.setChecked(true);
                break;
            case 2:
                mRadioBut3.setChecked(true);
                break;
            case 3:
                mRadioBut4.setChecked(true);
                break;
            case 4:
                mRadioBut1.setChecked(true);
                break;
            case 5:
                mRadioBut2.setChecked(true);
                break;
            case 6:
                mRadioBut3.setChecked(true);
                break;
            case 7:
                mRadioBut4.setChecked(true);
                break;
            case 8:
                mRadioBut1.setChecked(true);
                break;
            case 9:
                mRadioBut2.setChecked(true);
                break;
            case 10:
                mRadioBut3.setChecked(true);
                break;
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void setImage(HomePageBean homePageBean) {
        for (int i = 0; i < homePageBean.getData().getBigImg().size(); i++) {
            Glide.with(getContext()).load(homePageBean.getData().getBigImg().get(i++).getImage()).into(mImage1);
            Glide.with(getContext()).load(homePageBean.getData().getBigImg().get(i++).getImage()).into(mImage2);
            Glide.with(getContext()).load(homePageBean.getData().getBigImg().get(i++).getImage()).into(mImage3);
            Glide.with(getContext()).load(homePageBean.getData().getBigImg().get(i++).getImage()).into(mImage4);
        }
    }

    @Override
    public void setText(HomePageBean homePageBean) {
        for (int i = 0; i < homePageBean.getData().getBigImg().size(); i++) {
            mLunBoTitle1.setText(homePageBean.getData().getBigImg().get(i++).getTitle());
            mLunBoTitle2.setText(homePageBean.getData().getBigImg().get(i++).getTitle());
            mLunBoTitle3.setText(homePageBean.getData().getBigImg().get(i++).getTitle());
            mLunBoTitle4.setText(homePageBean.getData().getBigImg().get(i++).getTitle());
        }
    }

    @Override
    public void setMsg(String msg) {

    }

    @Override
    public void setBasePresenter(HomeContract.Presenter presenter) {
        Log.i("setBasePresenter", "执行了setBasePresenter方法");
        this.presenter = presenter;
    }
}