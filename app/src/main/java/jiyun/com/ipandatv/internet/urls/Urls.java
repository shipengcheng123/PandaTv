package jiyun.com.ipandatv.internet.urls;

/**
 * Created by lx on 2017/7/11.
 * 接口文档
 */

public class Urls {
    public static final String PANDAURL = "http://www.ipanda.com/kehuduan/";
    //总标签页
    public static final String TOTALLABELURL = PANDAURL + "PAGE1450172284887217/index.json";
    //互动集合页
    public static final String INTERACTURL = PANDAURL + "PAGE14501767715521482/index.json";
    //熊猫首页    VIDE:单视频    VIDA：多视频
    public static final String PANDATOTAL = PANDAURL + "PAGE14501749764071042/index.json";

    //熊猫观察head URl
    public static final String PANDA_OBSERVE_HEAD = PANDAURL + "PAGE14503485387528442/index.json";
    //熊猫观察item URl
    public static final String PANDA_OBSERVE_ITEM = "http://api.cntv.cn/apicommon/index?path=iphoneInterface/general/getArticleAndVideoListInfo.json&primary_id=PAGE1449807494852603,PAGE1451473625420136,PAGE1449807502866458,PAGE1451473627439140,PAGE1451473547108278,PAGE1451473628934144&serviceId=panda";



    //熊猫直播
    public static final String PANDALIVE = PANDAURL + "PAGE14501769230331752/index.json";
    //熊猫直播多视角
    public static  final String DUOSHIJIAO="http://www.ipanda.com/kehuduan/PAGE14501769230331752/PAGE14501787896813312/index.json";
    //熊猫直播标签
    public static final String PANDALIVEBQ = PANDAURL + "PAGE14501772263221982/index.json";
    //直播中国Tab
    public static final String PANDA_LIVE_CHINA_TAB=PANDAURL+"PAGE14501775094142282/index.json";
    //熊猫首页
    public static final String HOME_URL = PANDAURL+"PAGE14501749764071042/index.json";
    //CCTV
    public static final String CCTV = PANDAURL+"shipinliebieye/cctvshipindicengye/index.json";

    public static final String PANDACULTURE = "http://www.ipanda.com/kehuduan/xmwh/index.json";


    //互动集合
    public static final String HDJH = PANDAURL+"PAGE14501767715521482/index.json";
    //版本跟新
    public static final String UPDATE_URL = "http://115.182.9.124/index.php?action=release-GetNewVersions&applyName=1426217325";

    public static final String LOGIN_URL="https://reg.cntv.cn/login/login.action";

    //获取边看边聊列表
    public static final String TALKLIST = "http://newcomment.cntv.cn/comment/list";
    //验证码
    public static final String IMAGE_CODE = "http://reg.cntv.cn/simple/verificationCode.action";

    //视频播放path
    public static final String VADIOPATH = "http://vdn.apps.cntv.cn/api/getVideoInfoForCBox.do";

    //注册
    public static final String REGISTER_URL="https://reg.cntv.cn/api/register.action";


}
