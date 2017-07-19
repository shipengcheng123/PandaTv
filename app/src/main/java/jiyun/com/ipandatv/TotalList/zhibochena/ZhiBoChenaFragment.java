package jiyun.com.ipandatv.TotalList.zhibochena;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.adapter.DragAdapter;
import jiyun.com.ipandatv.adapter.zhibochena.ZHPagerAdapter;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.tile_right.Title_RightActivity;
import jiyun.com.ipandatv.fragment.pandadirect.adapter.PandaDirectAdapter;
import jiyun.com.ipandatv.fragment.zhibochena.BadaLingFragment;
import jiyun.com.ipandatv.model.entity.zhibochena.PopupBean;
import jiyun.com.ipandatv.view.DragGridView;

import static jiyun.com.ipandatv.R.id.live_chena_IBtn;

/**
 * Created by Lenovo on 2017/7/12.
 */

public class ZhiBoChenaFragment extends BaseFragment implements ZhiBoChenaContract.View {


    @BindView(R.id.Personal_Cente)
    ImageView PersonalCente;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.live_chena_TabLayout)
    TabLayout liveChenaTabLayout;
    @BindView(live_chena_IBtn)
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
    private List<String> mListNameUrl;
    private Map<String,String> mMapAllUrl;
    private ZhiBoChenaPresenter presenter;
    private CheckBox button;

    @Override
    protected int getLayoutId() {
        return R.layout.zhibochena_fragment;
    }

    @Override
    protected void init(View view) {
        new ZhiBoChenaPresenter(this);
        presenter.start();

        title_adapter = new ZHPagerAdapter(App.activity.getSupportFragmentManager(), fragmentList, channels);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }

    }

    @Override
    protected void loadData() {


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

    /*    @OnClick(live_chena_IBtn)
        public void onViewClicked() {
    //        Intent intent = new Intent(getActivity(), LiveChinaAdd.class);
    //        startActivity(intent);


        }*/
    public void add_Fragment(PopupBean popupBean) {
        mListName = new ArrayList<>();
        mList = new ArrayList<>();
        mListNameUrl = new ArrayList<>();
        mMapAllUrl = new HashMap<>();
        List<PopupBean.TablistBean> tablist = popupBean.getTablist();
        List<PopupBean.AlllistBean> alllist = popupBean.getAlllist();
        BadaLingFragment badaLingFragment = null;
        Bundle bundle = null;
        for (PopupBean.TablistBean tablistBean : tablist) {
            mListName.add(tablistBean.getTitle());
            badaLingFragment = new BadaLingFragment();
            bundle = new Bundle();
            bundle.putString("url", tablistBean.getUrl());
            badaLingFragment.setParams(bundle);
            mList.add(badaLingFragment);
            mListNameUrl.add(tablistBean.getUrl());
            mMapAllUrl.put(tablistBean.getTitle(), tablistBean.getUrl());
        }
        for (PopupBean.AlllistBean alllistBean : alllist) {
            mMapAllUrl.put(alllistBean.getTitle(), alllistBean.getUrl());
        }


        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        liveChenaViewPager.setAdapter(adapter);
        liveChenaTabLayout.setupWithViewPager(liveChenaViewPager);
    }

    public void setRefresh() {
        mListName.clear();
        mList.clear();
        mListName.addAll(channels) ;
        Set<String> strings = mMapAllUrl.keySet();

        BadaLingFragment badaLingFragment = null;
        Bundle bundle = null;
        String url=null;
        for (String nameTab : mListName) {
            url = mMapAllUrl.get(nameTab);
            badaLingFragment = new BadaLingFragment();
            bundle = new Bundle();
            bundle.putString("url", url);
            badaLingFragment.setParams(bundle);
            mList.add(badaLingFragment);
        }
        adapter = new PandaDirectAdapter(getChildFragmentManager(), mListName, mList);
        liveChenaViewPager.setAdapter(adapter);
        liveChenaTabLayout.setupWithViewPager(liveChenaViewPager);

    }


    public void upPopupWindow(View view) {
        View v = LayoutInflater.from(App.activity).inflate(R.layout.activity_popup_columns, null);
        popupView(v);
        popupWindow = new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); // 响应返回键必须的语句
        popupWindow.showAsDropDown(view, 0, 0);
    }

    public void popupView(View v) {
        ImageView imageView = (ImageView) v.findViewById(R.id.Fanhui);
        gridView = (DragGridView) v.findViewById(R.id.gridView_channel);
        gridView_other = (DragGridView) v.findViewById(R.id.gridView_channel_other);
        button = (CheckBox) v.findViewById(R.id.licechina_add_button);
        gridView.setNumColumns(3);
        dragAdapter = new DragAdapter(App.activity, channels);
        gridView.setAdapter(dragAdapter);

        other_adapter = new DragAdapter(App.activity, channels_other);
        gridView_other.setAdapter(other_adapter);
        gridView_other.setNumColumns(3);

        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    button.setText("完成");
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String channel = channels.get(position);
                            if(channels.size()>4) {
                                channels.remove(position);
                                channels_other.add(channel);
                                dragAdapter.notifyDataSetChanged();
                                other_adapter.notifyDataSetChanged();
                            }

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
                        }
                    });
                } else {
                    button.setText("编辑");
                    setRefresh();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });




    }

    private void initDataOther(List<PopupBean.AlllistBean> alllistBeanList) {
        for (PopupBean.AlllistBean alllistBean : alllistBeanList) {
            channels_other.add(alllistBean.getTitle());
        }

    }

    private void initDatatitle(List<PopupBean.TablistBean> tablistBeanList) {
        for (PopupBean.TablistBean alllistBean : tablistBeanList) {
            channels.add(alllistBean.getTitle());
        }

    }


    @OnClick(R.id.Personal_Cente)
    public void onClicked() {

        Intent in = new Intent(getActivity(), Title_RightActivity.class);
        startActivity(in);

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void getChinaLiveTab(PopupBean popupBean) {
        add_Fragment(popupBean);
        List<PopupBean.TablistBean> tablist = popupBean.getTablist();
        List<PopupBean.AlllistBean> alllist = popupBean.getAlllist();
        initDatatitle(tablist);
        initDataOther(alllist);
        liveChenaIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.zhibochena_fragment, null);
                upPopupWindow(view);
            }
        });
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setBasePresenter(ZhiBoChenaContract.Presenter presenter) {
        this.presenter = (ZhiBoChenaPresenter) presenter;
    }

}
