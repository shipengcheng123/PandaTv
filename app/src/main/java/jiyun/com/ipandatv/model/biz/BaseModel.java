package jiyun.com.ipandatv.model.biz;

import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.IHttp;

/**
 * Created by lx on 2017/7/11.
 */

public interface BaseModel {
    public static IHttp iHttp = HttpFactory.create();
}
