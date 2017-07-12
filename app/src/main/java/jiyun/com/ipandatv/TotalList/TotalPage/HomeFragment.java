package jiyun.com.ipandatv.TotalList.TotalPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jiyun.com.ipandatv.R;
import jiyun.com.ipandatv.base.BaseFragment;

/**
 * Created by lx on 2017/7/11.
 * 实现契约中的view方法
 * 拿到从presenter层网络请求出来的东西并放上去
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    HomeContract.Presenter presenter;
    @BindView(R.id.Text)
    TextView Text;

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void loadData() {
        presenter.start();
    }

    @Override
    public void setBasePresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setText(PandaBean pandaBean) {
        Text.setText(pandaBean.getTab().get(1).getTitle());
    }

    @Override
    public void showmsg(String errormsg) {
        Log.i("错误信息", errormsg);
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
}
