package jiyun.com.ipandatv.fragment.zhibochena;

import jiyun.com.ipandatv.internet.callback.INetWorkCallback;
import jiyun.com.ipandatv.model.biz.Zhibochena.ZhiboChenaModel;
import jiyun.com.ipandatv.model.biz.Zhibochena.zhiboChenaModelImpl;
import jiyun.com.ipandatv.model.entity.zhibochena.ChangchengBean;

/**
 * Created by Lenovo on 2017/7/18.
 */

public class BadaLingFragmentPresenter implements BadaLingFragmentCotract.Presenter {
    private BadaLingFragmentCotract.View view;
    private ZhiboChenaModel zhiboChenaModel;
    public BadaLingFragmentPresenter(BadaLingFragmentCotract.View view){
        this.view=view;
        this.view.setBasePresenter(this);
        zhiboChenaModel=new zhiboChenaModelImpl();
    }
    @Override
    public void start() {

    }

    @Override
    public void setUrl(String url) {
        zhiboChenaModel.getLiveChinaUrl(url, new INetWorkCallback<ChangchengBean>() {
            @Override
            public void OnSucess(ChangchengBean changchengBean) {
                view.getManager(changchengBean);
            }

            @Override
            public void OnError(int ErrorCode, String ErrorMsg) {

            }
        });
    }
}
