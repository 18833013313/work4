package bw.com.zhoukao1.controct;

import java.util.HashMap;

import bw.com.zhoukao1.entivity.UserBean;

public class GoodsControcent {
    public abstract interface ILogPerson{
        public abstract void login(HashMap<String,String> params);
    }

    public interface ILogView{
        void OnFail(String msg);
        void OnSuccess(UserBean userBean);
    }
}
