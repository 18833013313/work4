package bw.com.lx_zk1_mn2.presenter;

import java.util.HashMap;

import bw.com.lx_zk1_mn2.contract.LogContract;
import bw.com.lx_zk1_mn2.entity.UserBean;
import bw.com.lx_zk1_mn2.model.LogModel;
import bw.com.lx_zk1_mn2.net.RequesCallBack;

public class Logpresentere extends LogContract.LogPresenter{
    private LogModel logModel;
    private LogContract.LogView logView;
    public void login(HashMap<String,String> parmas) {
        if (logModel!=null){
            logModel.login(parmas, new RequesCallBack() {
                @Override
                public void failure(String msg) {
                    if (logView!=null){
                        logView.failure(msg);
                    }
                }

                @Override
                public void successMsg(String msg) {
                    logView.successMsg(msg);
                }

                @Override
                public void success(UserBean userBean) {
                    logView.success(userBean);
                }

                @Override
                public void success(String result) {

                }
            });
        }
    }
}
