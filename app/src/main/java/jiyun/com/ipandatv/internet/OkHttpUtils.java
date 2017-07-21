package jiyun.com.ipandatv.internet;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import jiyun.com.ipandatv.App;
import jiyun.com.ipandatv.activity.ACache;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKhttp发送网络请求
 * Created by lx on 2017/7/11.
 */

public class OkHttpUtils implements IHttp {

    private OkHttpClient okHttpClient;

    //构造函数私有化
    private OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    private static OkHttpUtils okHttpUtils;

    //提供一个公共的、静态的、返回值类型是当前本类的对象
    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null)
                    okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

    /**
     * 发送get请求
     *
     * @param url      请求地址
     * @param params   请求参数
     * @param callback 回调
     * @param <T>      请求回来的数据对应的JavaBean
     */
    @Override
    public <T> void get(String url, Map<String, String> params, final INetWorkCallback<T> callback) {
        StringBuffer sb = new StringBuffer(url);
        if (params != null && params.size() > 0) {
            sb.append("?");
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                sb.append(key).append("=").append(value).append("&");
            }
            url = sb.toString().substring(0, sb.length() - 1);
        }
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.OnError(404, e.getMessage().toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.OnSucess(getGeneric(jsonData, callback));
                    }
                });
            }
        });

    }

    @Override
    public <T> void post(String url, Map<String, String> params, final INetWorkCallback<T> callback) {

        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                String value = params.get(key);
                builder.add(key, value);
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.OnError(404, e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                //执行在子线程中
                App.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //执行在主线程
                        callback.OnSucess(getGeneric(jsonData, callback));
                    }
                });

            }
        });
    }


    /**
     * 自动解析json至回调中的JavaBean
     *
     * @param jsonData
     * @param callBack
     * @param <T>
     * @return
     */
    private <T> T getGeneric(String jsonData, INetWorkCallback<T> callBack) {
        Gson gson = new Gson();
        //通过反射获取泛型的实例
        Type[] types = callBack.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        T t = gson.fromJson(jsonData, type);
        ACache aCache = ACache.get(App.activity);
        aCache.put(t.getClass().getSimpleName(), (Serializable) t);
        return t;
    }

    public String getCookie() {
        SharedPreferences sharedPreferences = App.context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("cookie", "");
        return cookie;
    }
}
