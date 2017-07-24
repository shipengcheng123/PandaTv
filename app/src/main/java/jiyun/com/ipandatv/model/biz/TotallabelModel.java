package jiyun.com.ipandatv.model.biz;

import jiyun.com.ipandatv.fragment.Home.bean.UpdateBean;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.internet.callback.NNetWorkCallback;
import jiyun.com.ipandatv.internet.callback.NetWorkCallback;
import jiyun.com.ipandatv.model.entity.HomePageBean;
import jiyun.com.ipandatv.model.entity.LoginBean;
import jiyun.com.ipandatv.model.entity.PandaBean;

/**
 * Created by lx on 2017/7/11.
 * 处理业务逻辑（数据读写）
 */

public interface TotallabelModel extends BaseModel {
    void getTotallabel(INetWorkCallback<PandaBean> callback);

    //首页轮播图接口
    void getHomePageLunBo(INetWorkCallback<HomePageBean> callback);

    //登陆
    void Login(String user, String pwd, INetWorkCallback<LoginBean> callback);

    //获取手机验证码
    void getYzm(String name, String imgYzm, NNetWorkCallback callback);

    //注册  找回密码
    void getRegister(String name, String Yzm, String Pwd, NNetWorkCallback callback);

    //获取图片验证码
    void getPicture(NetWorkCallback callback);

    //邮箱注册
    void getEmailRegister(String name, String Yzm, String Pwd, NNetWorkCallback callback);

    void version(INetWorkCallback<UpdateBean> callback);
}
