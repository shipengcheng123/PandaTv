package jiyun.com.ipandatv.fragment.pandabroadcast;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.pandabroadcast.adapter.PandaCultureBannerAdapter;
import jiyun.com.ipandatv.fragment.pandabroadcast.adapter.PandaCultureItemAdapter;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.CultureContract;
import jiyun.com.ipandatv.fragment.pandabroadcast.culturecontract.PandaCulturePresenter;
import jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture.PandaCultureEntity;

/**
 * Created by INS7566 on 2017/7/14.
 */

public class PandaCultureFragment extends BaseFragment implements CultureContract.View {
    @BindView(R.id.culture_pullrecycler)
    PullToRefreshRecyclerView culturePullrecycler;
    Unbinder unbinder;
    private PandaCulturePresenter pandaCulturePersenter;

    private List<PandaCultureEntity.BigImgBean> dataBeanList;
    private List<ImageView> imgs;
    private PandaCultureBannerAdapter pandaCultureBannerAdapter;
    private ViewPager pandaCultureViewPagerView;
    private TextView pandaCultureBannerTitle;
    private List<PandaCultureEntity.ListBean> listBeanList;
    private PandaCultureItemAdapter itemAdapter;
    private List<CircleImageView> points;
    private int currentPosition = 10000;
    private ViewGroup pointsLinearLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_culture;
    }

    @Override
    protected void init(View view) {
        imgs = new ArrayList<>();
        points = new ArrayList<>();
        listBeanList = new ArrayList<>();
        pandaCulturePersenter = new PandaCulturePresenter(this);
        dataBeanList = new ArrayList<>();

        itemAdapter = new PandaCultureItemAdapter(getActivity(), listBeanList);
        culturePullrecycler.setAdapter(itemAdapter);


        View pandaCultureView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_panda_culture_banner, null);
        pandaCultureViewPagerView = (ViewPager) pandaCultureView.findViewById(R.id.panda_culture_banner);
        pandaCultureBannerTitle = (TextView) pandaCultureView.findViewById(R.id.panda_culture_banner_title);
        pointsLinearLayout = (ViewGroup) pandaCultureView.findViewById(R.id.pointsLinearLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        culturePullrecycler.setLayoutManager(linearLayoutManager);
        culturePullrecycler.addHeaderView(pandaCultureView);
        culturePullrecycler.setPullRefreshEnabled(false);
        culturePullrecycler.setLoadingMoreEnabled(false);
    }

    @Override
    protected void loadData() {
        pandaCulturePersenter.start();


        pandaCultureViewPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (CircleImageView circleImageView : points) {
                    circleImageView.setImageResource(R.drawable.white_point);
                }
                points.get(position % points.size()).setImageResource(R.drawable.gray_point);
                pandaCultureBannerTitle.setText(dataBeanList.get(position % points.size()).getTitle());
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void setParams(Bundle bundle) {

    }

    @Override
    public void showAll(PandaCultureEntity entity) {
        dataBeanList.addAll(entity.getBigImg());
        listBeanList.addAll(entity.getList());
        createImg(entity);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBasePresenter(Object o) {

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    currentPosition = currentPosition + 1;
                    pandaCultureViewPagerView.setCurrentItem(currentPosition);
                    sendEmptyMessageDelayed(1, 3000);
                    break;
            }
        }
    };

    private void createImg(PandaCultureEntity entity) {
        List<PandaCultureEntity.BigImgBean> tab = entity.getBigImg();
        int pointPosition = 0;
        for (int i = 0; i < tab.size(); i++) {
            PandaCultureEntity.BigImgBean bigImgBean = tab.get(i);
            String image = bigImgBean.getImage();
            ImageView imageView = new ImageView(getActivity());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this).load(image).into(imageView);
            imgs.add(imageView);


            CircleImageView circleImageView = new CircleImageView(getActivity());
            circleImageView.setCircleModel(CircleImageView.POINT);
            RelativeLayout.LayoutParams pointViewParams = new RelativeLayout.LayoutParams(dp2Px(8), dp2Px(8));
            circleImageView.setLayoutParams(pointViewParams);
            circleImageView.setTag(pointPosition);
            circleImageView.setImageResource(R.drawable.white_point);
            points.add(circleImageView);
            pointsLinearLayout.addView(circleImageView);
            pointPosition++;

        }
        pandaCultureBannerAdapter = new PandaCultureBannerAdapter(imgs);
        points.get(0).setImageResource(R.drawable.gray_point);
        pandaCultureViewPagerView.setAdapter(pandaCultureBannerAdapter);
        pandaCultureBannerAdapter.notifyDataSetChanged();
        pandaCultureViewPagerView.setCurrentItem(currentPosition);
        handler.sendEmptyMessageDelayed(1, 3000);

    }
    public int dp2Px(int dpValue) {
        return (int) (getActivity().getResources().getDisplayMetrics().density * dpValue + 0.5f);
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
