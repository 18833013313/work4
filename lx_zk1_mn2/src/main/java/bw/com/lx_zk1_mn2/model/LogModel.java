package bw.com.lx_zk1_mn2.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.HashMap;

import bw.com.lx_zk1_mn2.api.UserApi;
import bw.com.lx_zk1_mn2.entity.UserBean;
import bw.com.lx_zk1_mn2.net.RequesCallBack;

public class LogModel {
    Handler handler = new Handler();
    public void login(HashMap<String,String> parmas, final RequesCallBack callBack){
        OkhttpUtils.getInstance().post(UserApi.api, parmas, new OkhttpCallBack() {
            @Override
            public void failure(String msg) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.failure("请求失败");
                        }
                    });
                }
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                        strean2String(result,callBack);
                }
            }
        });
    }

    /**
     * 解析数据
     * @param result
     * @param callBack
     */
    private void strean2String(String result, final RequesCallBack callBack) {
        final UserBean userBean = new Gson().fromJson(result, UserBean.class);

        if (callBack!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(userBean);
                    }
                });
            }
    }
}
