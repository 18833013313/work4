package bw.com.lx_zk1_mn4.view;

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

import bw.com.lx_zk1_mn4.R;
import bw.com.lx_zk1_mn4.adapter.MAdapter;
import bw.com.lx_zk1_mn4.api.UserApi;
import bw.com.lx_zk1_mn4.contract.LogContract;
import bw.com.lx_zk1_mn4.entity.UserBean;
import bw.com.lx_zk1_mn4.net.OkhttpCallBack;
import bw.com.lx_zk1_mn4.presenter.LogPresenter;
import bw.com.lx_zk1_mn4.util.OkhttpUtil;

public class MainActivity extends AppCompatActivity implements LogContract.ILogView {

    private RecyclerView rlv;
    private Button button;
    private int page=1;
    private String api;
    private MAdapter mAdapter;
    private List<UserBean.DataBean> list;
    private LogPresenter logPresenter;
    private UserBean userBean;
    private UserBean userBean1;
    private MAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("keywords","手机");
                params.put("page",page+"");
                logPresenter.login(params);

                rlv.setAdapter(mAdapter1);
                /*new OkhttpUtil().post(api, params, new OkhttpCallBack() {
                    @Override
                    public void onFile(String msg) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        setram2String(result);
                    }
                });*/
               // new
            }


        });
    }

    private void setram2String(String result) {
        UserBean userBean = new Gson().fromJson(result, UserBean.class);
        if (userBean!=null){

            rlv.setAdapter(mAdapter1);
        }
    }

    private void initView() {
        button = findViewById(R.id.button);
        rlv = findViewById(R.id.rlv);
        logPresenter = new LogPresenter(this);
        api = new UserApi().api;
        list = new ArrayList<>();
        userBean = new UserBean();

        rlv.setLayoutManager(new LinearLayoutManager(this,1,false));
    }

    @Override
    public void OnFail(String msg) {

    }

    @Override
    public void OnSuccess(UserBean userBean) {

        mAdapter1 = new MAdapter(this,userBean.getData());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkhttpUtil.getInstance().deso();
    }
}
