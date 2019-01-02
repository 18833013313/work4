package bw.com.lx_zk1_mn2.net;

import bw.com.lx_zk1_mn2.entity.UserBean;

public interface RequesCallBack {
    void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
    void successMsg(String msg);//请求成功，但数据不正确
    void success(UserBean userBean);//数据合法
    void success(String result);//数据合法
}
