package bw.com.lx_zk1_mn2.model;

public interface OkhttpCallBack {
    void failure(String msg);//服务器请求失败：断网，服务器宕机等等因素
    void success(String result);//数据合法
}
