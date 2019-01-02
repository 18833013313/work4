package bw.com.lx_zk1_mn4.presenter;

import java.util.HashMap;

import bw.com.lx_zk1_mn4.api.UserApi;
import bw.com.lx_zk1_mn4.contract.LogContract;
import bw.com.lx_zk1_mn4.entity.UserBean;
import bw.com.lx_zk1_mn4.model.LogModel;
import bw.com.lx_zk1_mn4.net.RequestCallBack;

public class LogPresenter implements LogContract.ILogPerson{

    private final LogModel logModel;
    private final LogContract.ILogView iLogView;
    public LogPresenter(LogContract.ILogView iLogView){
        logModel = new LogModel();
        this.iLogView = iLogView;
}

    @Override
    public void login(HashMap<String, String> params) {
              logModel.login(params,UserApi.api, new RequestCallBack() {
                  @Override
                  public void Fail(String msg) {

                  }

                  @Override
                  public void Success(UserBean userBean) {
                        if (iLogView!=null){
                           iLogView.OnSuccess(userBean);
                        }
                  }
              });
    }
}
