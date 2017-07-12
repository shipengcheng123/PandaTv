package jiyun.com.ipandatv;

import android.app.Application;
import android.content.Context;
import android.widget.RadioGroup;

import jiyun.com.ipandatv.base.BaseActivity;
import jiyun.com.ipandatv.base.BaseFragment;

/**
 * Created by lx on 2017/7/11.
 * 进入App进行初始化操作
 */

public class App extends Application {
    public static BaseActivity activity;
    public static BaseFragment lastFragment;
    public static RadioGroup mRadiogroup;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
