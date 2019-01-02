package bw.com.lx_zk1_mn2.contract;

import java.util.HashMap;

import bw.com.lx_zk1_mn2.entity.UserBean;
import bw.com.lx_zk1_mn2.net.RequesCallBack;

public interface LogContract {
    public abstract class LogPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface LogModel{
        void login(HashMap<String,String> params, RequesCallBack callBack);
    }
    interface LogView{
        void mobileError(String msg);
        void pwdError(String msg);
        void failure(String msg);
        void success(UserBean userBean);
        void successMsg(String msg);
    }
}
