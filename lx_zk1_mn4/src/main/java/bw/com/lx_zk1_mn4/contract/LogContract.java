package bw.com.lx_zk1_mn4.contract;

import java.util.HashMap;

import bw.com.lx_zk1_mn4.entity.UserBean;
import bw.com.lx_zk1_mn4.net.RequestCallBack;

public interface LogContract {
        public abstract interface ILogPerson{
            public abstract void login(HashMap<String,String> params);
        }
        public interface ILogModel{
            void setokhttp(HashMap<String,String> params, String string, RequestCallBack requestCallBack);
        }
        public interface ILogView{
            void OnFail(String msg);
            void OnSuccess(UserBean userBean);
        }

}
