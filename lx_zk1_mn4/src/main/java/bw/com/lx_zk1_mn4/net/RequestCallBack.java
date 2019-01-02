package bw.com.lx_zk1_mn4.net;

import bw.com.lx_zk1_mn4.entity.UserBean;

public interface RequestCallBack {
    void Fail(String msg);

    void Success(UserBean userBean);

}
