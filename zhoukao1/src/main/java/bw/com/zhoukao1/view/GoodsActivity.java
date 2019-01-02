package bw.com.zhoukao1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bw.com.zhoukao1.R;
import bw.com.zhoukao1.adapter.MAdapter;
import bw.com.zhoukao1.api.UserApi;
import bw.com.zhoukao1.entivity.UserBean;
import bw.com.zhoukao1.net.OkhttpCallBack;
import bw.com.zhoukao1.util.OkHttpWork;

public class GoodsActivity extends AppCompatActivity {

    private RecyclerView relativ;
    private String api;
    private List<UserBean.DataBean.BannerBean> list;
    private MAdapter mAdapter;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
        initData();

    }

    private void initData() {
        HashMap<String,String> params = new HashMap<>();
        params.put("tuijian","tuijian");
        params.put("page",page+"");
        OkHttpWork.getInstanct().post(api, params,new OkhttpCallBack() {
            @Override
            public void onFile(String msg) {
                Toast.makeText(GoodsActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSussuce(String reques) {
                  streamString(reques);
            }
        });
    }



    private void initView() {
        relativ = findViewById(R.id.relativ);
        api = UserApi.api;
        list = new ArrayList<>();
        relativ.setLayoutManager(new GridLayoutManager(this,2));

    }
    private void streamString(String result) {
        UserBean userBean = new Gson().fromJson(result, UserBean.class);
        mAdapter = new MAdapter(this,userBean.getData().getTuijian().getList());
        relativ.setAdapter(mAdapter);

    }
}
