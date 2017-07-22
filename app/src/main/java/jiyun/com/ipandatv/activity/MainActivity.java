package jiyun.com.ipandatv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.TotalList.broadcast.BobaoFragment;
import jiyun.com.ipandatv.TotalList.zhibochena.ZhiBoChenaFragment;
import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.config.ConfigFragment;
import jiyun.com.ipandatv.fragment.Home.HomeFragment;
import jiyun.com.ipandatv.fragment.pandabroadcast.PandaCultureFragment;
import jiyun.com.ipandatv.fragment.pandadirect.PandadirectFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.FrameLayout)
    android.widget.FrameLayout FrameLayout;
    @BindView(R.id.btn_explore_comprehensive)
    RadioButton btnExploreComprehensive;
    @BindView(R.id.btn_explore_move)
    RadioButton btnExploreMove;
    @BindView(R.id.btn_explore_plus)
    RadioButton btnExplorePlus;
    @BindView(R.id.btn_explore_find)
    RadioButton btnExploreFind;
    @BindView(R.id.btn_explore_my)
    RadioButton btnExploreMy;
    @BindView(R.id.FrameLayout_contentGroup)
    RadioGroup FrameLayoutContentGroup;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentManager fragmentmanager;
    private HomeFragment homeFragment;
    private View view;
    private AlertDialog isExit;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fragmentmanager = getSupportFragmentManager();
        App.mRadiogroup = (RadioGroup) findViewById(R.id.FrameLayout_contentGroup);
    }

    @Override
    public void initData() {
        TextView tv = new TextView(this);
        tv.setText("MainActivity");
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if(bundle!=null){
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tv.setText("Title : " + title + "  " + "Content : " + content);

        }
        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    }

    @Override
    public void loadData() {
        ConfigFragment.getInstance().init().start(HomeFragment.class).build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_explore_comprehensive, R.id.btn_explore_move, R.id.btn_explore_plus, R.id.btn_explore_find, R.id.btn_explore_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_explore_comprehensive:
                initView();
                ConfigFragment.getInstance().init().start(HomeFragment.class).build();
                break;
            case R.id.btn_explore_move:
                initView();
                ConfigFragment.getInstance().init().start(PandadirectFragment.class).build();
                break;
            case R.id.btn_explore_plus:
                initView();
                ConfigFragment.getInstance().init().start(PandaCultureFragment.class).build();
                break;
            case R.id.btn_explore_find:
                ConfigFragment.getInstance().init().start(BobaoFragment.class).build();

                break;
            case R.id.btn_explore_my:

                ConfigFragment.getInstance().init().start(ZhiBoChenaFragment.class).build();

                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog,null);
            Button btnquxiao = (Button) view.findViewById(R.id.dialog_quxiao);
            Button btntuichu = (Button) view.findViewById(R.id.dialog_tuichu);
            btnquxiao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isExit.dismiss();

                }
            });
            btntuichu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                }
            });


            // 创建退出对话框
            isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setView(view);
            isExit.show();

        }

        return false;

    }



    @Override
    public void onBackPressed() {
        FragmentManager.BackStackEntry entryAt = fragmentmanager.getBackStackEntryAt(fragmentmanager.getBackStackEntryCount() - 1);
        //得到每一个位于栈顶的类的名字，然后执行Finish方法进行弹栈
        String name = entryAt.getName();
        if ("HomeFragment".equals(name) ||
                "BuyTicketsFragment".equals(name) ||
                "PandadirectFragment".equals(name) ||
                "PandaCultureFragment".equals(name) ||
                "ZhiBoChenaFragment".equals(name)
                ) {



//           finish();
            Process.killProcess(Process.myPid());
            System.exit(0);


        }else{
            if (fragmentmanager.getBackStackEntryCount() > 1) {
                fragmentmanager.popBackStackImmediate();//执行弹栈，立马执行
                //否则记录得到位于栈顶的类名字
                String simpleName = fragmentmanager.getBackStackEntryAt(fragmentmanager.getBackStackEntryCount() - 1).getName();
                //记录做标记，标记为上一个Fragment,点击back键刷新lastFragment
                App.lastFragment = (BaseFragment) fragmentmanager.findFragmentByTag(simpleName);
            }
        }
    }


    //执行完全退出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());//获取pid
        System.exit(0);
    }

    //隐藏下面的RadioGroup
    public RadioGroup getMainRadioGroup() {
        return FrameLayoutContentGroup;
    }

    public void setMainRadioGroup(RadioGroup mainRadioGroup) {
        FrameLayoutContentGroup = mainRadioGroup;
    }

    //隐藏title的
    public void setTitleImage(ImageView titleImage) {

    }

}
