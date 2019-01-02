package bw.com.zhoukao1.util;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bw.com.zhoukao1.net.OkhttpCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpWork {
    private static OkHttpWork instanct;
    private final OkHttpClient okHttpClient;
    private int code = 1;
    Handler handler = new Handler();

    public OkHttpWork() {
        //拦截
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(httpLoggingInterceptor.getLevel());
        //okhttp网络请求
        okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpWork getInstanct() {
        if (instanct==null){
            synchronized (OkHttpWork.class){
                if (instanct==null){
                    instanct = new OkHttpWork();
                }
            }
        }
        return instanct;
    }
    public void post(String api,HashMap<String,String> params,final OkhttpCallBack callBack){
        FormBody.Builder forBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            forBody.add(p.getKey(),p.getValue());
        }
        FormBody build = forBody.build();
        Request request = new Request.Builder()
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
                            callBack.onSussuce(string);
                        }
                    });
                }
            }
        });

    }
    public void xielou(){

    }
}
