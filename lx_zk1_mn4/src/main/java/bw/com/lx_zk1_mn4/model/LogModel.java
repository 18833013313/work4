package bw.com.lx_zk1_mn4.model;

import com.google.gson.Gson;

import java.util.HashMap;

import bw.com.lx_zk1_mn4.api.UserApi;
import bw.com.lx_zk1_mn4.entity.UserBean;
import bw.com.lx_zk1_mn4.net.OkhttpCallBack;
import bw.com.lx_zk1_mn4.net.RequestCallBack;
import bw.com.lx_zk1_mn4.util.OkhttpUtil;

public class LogModel {
    public void login(HashMap<String, String> params, String api, final RequestCallBack requestCallBack){

        OkhttpUtil.getInstance().post(UserApi.api, params, new OkhttpCallBack() {
            @Override
            public void onFile(String msg) {
                requestCallBack.Fail("请求失败");
            }

            @Override
            public void onSuccess(String result) {
                UserBean userBean = stream2String(result);

                requestCallBack.Success(userBean);
            }
        });

    }

    private UserBean stream2String(String result) {
        UserBean userBean = new Gson().fromJson(result, UserBean.class);
        return userBean;
    }
}
