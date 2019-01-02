package bw.com.lx_zk1_mn1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bw.com.lx_zk1_mn1.R;
import bw.com.lx_zk1_mn1.adapter.MAdapter;
import bw.com.lx_zk1_mn1.api.UserApi;
import bw.com.lx_zk1_mn1.entity.UserBean;
import bw.com.lx_zk1_mn1.net.OkhttpCallBack;
import bw.com.lx_zk1_mn1.util.OkhttpUtil;

public class MainActivity extends AppCompatActivity{
    private int page = 1;
    /**
     * 请求商品列表
     */
    private Button mRequestProductBtn;
    private RecyclerView mRvProduct;
    private UserApi userApi;
    private String api;
    private List<UserBean.DataBean> list;
    private MAdapter mAdapter;
    private MAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        userApi = new UserApi();
        api = userApi.api;
        list = new ArrayList<>();

    }

    private void initView() {

        mRequestProductBtn = (Button) findViewById(R.id.requestProductBtn);
        mRvProduct = (RecyclerView) findViewById(R.id.rv_product);
        mRvProduct.setLayoutManager(new LinearLayoutManager(this,1,false));
        mRequestProductBtn.setOnClickListener(new View.OnClickListener() {
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

    private void getString(String result) {
        UserBean userBean = new Gson().fromJson(result, UserBean.class);
        if (userBean!=null){
            mAdapter1 = new MAdapter(this, userBean.getData());
            mRvProduct.setAdapter(mAdapter1);
        }
    }


}
