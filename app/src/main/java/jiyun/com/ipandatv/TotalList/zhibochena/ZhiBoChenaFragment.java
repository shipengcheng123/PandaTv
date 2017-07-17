package jiyun.com.ipandatv.TotalList.zhibochena;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.LiveChinaAdd;
import jiyun.com.ipandatv.adapter.DragAdapter;
import jiyun.com.ipandatv.adapter.zhibochena.ZHPagerAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;
import jiyun.com.ipandatv.fragment.zhibochena.BadaLingFragment;
import jiyun.com.ipandatv.fragment.zhibochena.EmeishanFragment;
import jiyun.com.ipandatv.fragment.zhibochena.FenghuangFragment;
import jiyun.com.ipandatv.fragment.zhibochena.HuangShanFragment;
import jiyun.com.ipandatv.fragment.zhibochena.TaiShanFragment;
import jiyun.com.ipandatv.fragment.zhibochena.ZhangjiajieFragment;
import jiyun.com.ipandatv.view.DragGridView;

/**
 * Created by Lenovo on 2017/7/12.
 */

public class ZhiBoChenaFragment extends BaseFragment {


    @BindView(R.id.Personal_Cente)
    ImageView PersonalCente;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.live_chena_TabLayout)
    TabLayout liveChenaTabLayout;
    @BindView(R.id.live_chena_IBtn)
    ImageButton liveChenaIBtn;
    @BindView(R.id.live_chena_viewPager)
    ViewPager liveChenaViewPager;
    Unbinder unbinder;
    private DragGridView gridView;
    private DragGridView gridView_other;
    private DragAdapter dragAdapter;
    private DragAdapter other_adapter;
    private ArrayList<BaseFragment> fragmentList = new ArrayList<BaseFragment>(); //碎片链表
    private PopupWindow popupWindow;
    private ArrayList<String> channels = new ArrayList<>();
    private ArrayList<String> channels_other = new ArrayList<>();
    private ZHPagerAdapter title_adapter;

    private PandaDirectAdapter adapter;
    private List<String> mListName;
    private List<BaseFragment> mList;


    @Override
    protected int getLayoutId() {
        return R.layout.zhibochena_fragment;
    }

    @Override
    protected void init(View view) {
        initDatatitle();
        initDataOther();
        title_adapter = new ZHPagerAdapter(App.activity.getSupportFragmentManager(), fragmentList, channels);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }
    }

    @Override
    protected void loadData() {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mList.add(new BadaLingFragment());
        mList.add(new TaiShanFragment());
        mList.add(new HuangShanFragment());
        mList.add(new FenghuangFragment());
        mList.add(new EmeishanFragment());
        mList.add(new ZhangjiajieFragment());

        mListName.add("八达岭");
        mListName.add("黟县");
        mListName.add("黄山");
        mListName.add("凤凰古城");
        mListName.add("峨眉山");
        mListName.add("泰山");


        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        liveChenaViewPager.setAdapter(adapter);
        liveChenaTabLayout.setupWithViewPager(liveChenaViewPager);

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

    @OnClick(R.id.live_chena_IBtn)
    public void onViewClicked() {
        Intent intent = new Intent(getContext(), LiveChinaAdd.class);
        startActivity(intent);


    }


    public void upPopupWindow() {
        View v = LayoutInflater.from(App.activity).inflate(R.layout.activity_popup_columns, null);
        popupView(v);
        popupWindow = new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public void popupView(View v) {

        gridView = (DragGridView) v.findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) v.findViewById(R.id.gridView_channel_other);

        gridView.setNumColumns(4);
        dragAdapter = new DragAdapter(App.activity, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(App.activity, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(4);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels.get(position);
                channels.remove(position);
                channels_other.add(channel);
                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
                title_adapter.notifyDataSetChanged();
            }
        });
        gridView_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String channel = channels_other.get(position);
                channels_other.remove(position);
                channels.add(channel);
                dragAdapter.notifyDataSetChanged();
                other_adapter.notifyDataSetChanged();
                title_adapter.notifyDataSetChanged();
            }
        });

    }

    private void initDataOther() {
        channels_other.add("张家界");
        channels_other.add("中央电视塔");
        channels_other.add("恒山悬空寺");
        channels_other.add("黄果树");
        channels_other.add("黄龙");
        channels_other.add("武夷山");
        channels_other.add("龙虎山");
        channels_other.add("嵩山少林寺");
        channels_other.add("承德避暑山庄");
        channels_other.add("敦煌月牙泉");
        channels_other.add("都江堰");
        channels_other.add("山海关");
        channels_other.add("天山");
        channels_other.add("乌镇");
        channels_other.add("青海湖鸟岛");
        channels_other.add("朱鹮");
        channels_other.add("丹霞山");
    }

    private void initDatatitle() {
        channels.add(0, "八达岭");
        channels.add(1, "凤凰古城");
        channels.add(2, "峨眉山");
        channels.add(3, "金丝猴");

    }


    @OnClick(R.id.Personal_Cente)
    public void onClicked() {

        Intent in = new Intent(getContext(), Title_RightActivity.class);
        startActivity(in);

    }
}
