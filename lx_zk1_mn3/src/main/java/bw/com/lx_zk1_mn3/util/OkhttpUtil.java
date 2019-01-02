package bw.com.lx_zk1_mn3.util;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bw.com.lx_zk1_mn3.net.OkhttpCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {
    private static OkhttpUtil instance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();
    public OkhttpUtil() {
        okHttpClient = new OkHttpClient
                .Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();

    }

    public static OkhttpUtil getInstance() {
        if (instance==null){
            synchronized (OkhttpUtil.class){
                if (instance==null){
                    instance = new OkhttpUtil();
                }
            }
        }
        return instance;
    }
    public void post(String api, HashMap<String,String> params, final OkhttpCallBack callBack){
        FormBody.Builder forBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
           forBody.add(p.getKey(),p.getValue());
        }
        FormBody build = forBody.build();
        Request request = new Request
                .Builder()
                .url(api)
                .post(build)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFile("请求失败");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();

                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(string);
                        }
                    });
                }
            }
        });
    }
}
