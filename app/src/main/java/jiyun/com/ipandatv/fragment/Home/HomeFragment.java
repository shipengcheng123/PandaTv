package jiyun.com.ipandatv.fragment.Home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.PullToRefreshRecyclerView;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.utils.L;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.activity.VideoActivity;
import jiyun.com.ipandatv.activity.YuanChuangActivity;
import jiyun.com.ipandatv.adapter.homepage.HomeViewPagerAdapter;
import jiyun.com.ipandatv.adapter.homepage.Home_Adapter;
import jiyun.com.ipandatv.adapter.homepage.setViewPagerListener;
import jiyun.com.ipandatv.base.BaseFragment;
import jiyun.com.ipandatv.fragment.Home.bean.UpdateBean;
import jiyun.com.ipandatv.fragment.Home.tile_right.BobaoActivity;
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
    //版本更新
    private static int versionCode;
    private String versionsUrl;
    private AlertDialog alertDialog;
    int total = 0;
    private int versionsInt;

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
        presenter.version();

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
                    String pid = bigImgBean.getPid();
                    String title = bigImgBean.getTitle();
                    Intent in = new Intent(App.activity, BobaoActivity.class);
                    in.putExtra("pid", pid);
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

    @Override
    public void getVersion(UpdateBean updateBean) {
        String versionsNum = updateBean.getData().getVersionsNum();
        versionsUrl = updateBean.getData().getVersionsUrl();
        versionsInt = Integer.parseInt(versionsNum);
        if (versionCode < versionsInt) {
            L.d("当前版本", versionCode + "");
            L.d("最新版本", versionsInt + "");
            getShowDialog();
        } else {
            Toast.makeText(getActivity(), "已经是最新版本", Toast.LENGTH_LONG).show();
        }
    }

    public void getShowDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("版本升级")//设置对话框标题
                .setMessage("检测到最新版本，新版本对系统做了更好的优化")//设置显示的内容
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {//添加确定按钮

                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        try {
                            PackageManager pm = getActivity().getPackageManager();
                            PackageInfo pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
                            pi.versionCode = versionsInt;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                        showDialogUpdate();
                        dialog.dismiss();
                    }
                }).setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();//在按键响应事件中显示此对话框
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
// 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("版本升级").
                // 设置提示框的图标
                        setIcon(R.drawable.logo_ipnda).
                // 设置要显示的信息
                        setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                        dialog.dismiss();
                        loadNewVersionProgress();//下载最新的版本程序
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final String uri = versionsUrl;
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Log.i("abc", "下载失败");
//                    Toast.makeText(getActivity(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), time + "updata.apk");
            if (!file.exists())
                file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }



}