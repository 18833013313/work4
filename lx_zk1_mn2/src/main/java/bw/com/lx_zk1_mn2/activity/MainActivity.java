package bw.com.lx_zk1_mn2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import bw.com.lx_zk1_mn2.R;
import bw.com.lx_zk1_mn2.contract.LogContract;
import bw.com.lx_zk1_mn2.entity.UserBean;
import bw.com.lx_zk1_mn2.presenter.Logpresentere;

public class MainActivity extends AppCompatActivity implements LogContract.LogView {

    private XRecyclerView mXrv;
    private int page = 1;
    private Logpresentere logpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        logpresenter = new Logpresentere();
    }

    private void initView() {

        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("keywords","手机");
        parmas.put("page",page+"");
        logpresenter.login(parmas);
    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(this,userBean+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
