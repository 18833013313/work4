package bw.com.lx_zk1_mn2.model;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static OkhttpUtils instance;
    private final OkHttpClient okHttpClient;
    Handler handler = new Handler();
    public OkhttpUtils() {
        okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();

    }

    public static OkhttpUtils getInstance() {
        if (instance==null){
            synchronized (OkhttpUtils.class){
                if (instance==null){
                    instance = new OkhttpUtils();
                }
            }
        }
        return instance;
    }
    public void post(String api, HashMap<String,String> parmas, final OkhttpCallBack callBack){
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : parmas.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }
        FormBody build = builder.build();

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
                            callBack.failure("请求失败");
                        }
                    });
                }

            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                if (callBack!=null){
                    if (200 == response.code()){
                        final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.success(result);
                            }
                        });
                    }
                }
            }
        });
    }
}
