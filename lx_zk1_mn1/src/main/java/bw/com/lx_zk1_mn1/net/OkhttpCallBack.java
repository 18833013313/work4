package bw.com.lx_zk1_mn1.net;

import bw.com.lx_zk1_mn1.entity.UserBean;

public interface OkhttpCallBack {
    void onFile(String msg);
    void onSuccess(String result);
}
