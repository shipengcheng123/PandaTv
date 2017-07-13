package jiyun.com.ipandatv.utils;

import android.util.Log;

/**
 * Created by Lenovo on 2017/7/13.
 */

public class MyLog {
    private static boolean isOpen = true;
    public static void e(String tag,String meg){
        if(isOpen) {
            Log.e(tag,meg);
        }
    }

}
