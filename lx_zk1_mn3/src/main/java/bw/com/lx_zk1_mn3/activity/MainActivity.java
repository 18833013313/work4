package bw.com.lx_zk1_mn3.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bw.com.lx_zk1_mn3.R;
import bw.com.lx_zk1_mn3.adapter.MAdapter;
import bw.com.lx_zk1_mn3.api.UserApi;
import bw.com.lx_zk1_mn3.entity.UserBean;
import bw.com.lx_zk1_mn3.net.OkhttpCallBack;
import bw.com.lx_zk1_mn3.util.OkhttpUtil;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvProduct;
    private Button button;
    private int page = 1;
    private List<UserBean.DataBean> list;
    private String api;
    private MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        mRvProduct.setLayoutManager(new LinearLayoutManager(this,1,false));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("keywords","手机");
                params.put("page",page+"");
                new OkhttpUtil().post(api, params, new OkhttpCallBack() {
                    @Override
                    public void onFile(String msg) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        getString(result);
                    }
                });
            }

        });
    }



    private void initView() {
        mRvProduct = findViewById(R.id.rv_product);
        button = findViewById(R.id.requestProductBtn);
        api = new UserApi().api;
        list = new ArrayList<>();


    }
    private void getString(String result) {
        UserBean userBean = new Gson().fromJson(result, UserBean.class);
        if (userBean!=null){
            mAdapter = new MAdapter(this, userBean.getData());
            mRvProduct.setAdapter(mAdapter);
        }
    }

}
