package jiyun.com.ipandatv.fragment.pandabroadcast.panda_culture;

import jiyun.com.ipandatv.internet.HttpFactory;
import jiyun.com.ipandatv.internet.callback.INetWorkCallback;

/**
 * Created by hp1 on 2017-04-07.
 */

public class PandaCultureImp implements IPandaCulture {
    @Override
    public void getPandaCultureHead(String url, INetWorkCallback myCallback) {
        HttpFactory.create().get(url,null,myCallback);
    }

    @Override
    public void getPandaCultureItem(String url, INetWorkCallback myCallback) {
        HttpFactory.create().get(url,null,myCallback);
    }
}
