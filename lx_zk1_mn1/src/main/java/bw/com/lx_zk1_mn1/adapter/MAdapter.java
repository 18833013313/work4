package bw.com.lx_zk1_mn1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bw.com.lx_zk1_mn1.R;
import bw.com.lx_zk1_mn1.entity.UserBean;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder> {
    private Context context;
    private List<UserBean.DataBean> list;

    public MAdapter(Context context, List<UserBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<UserBean.DataBean> list) {
        this.list = list;
    }

    /**
     * 创建viewHolder
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定
     * @param viewHolder
     * @param
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        UserBean.DataBean dataBean = list.get(postion);
        viewHolder.title.setText(dataBean.toString());
        String images = dataBean.getImages();
        String[]  imgAttr = images.split("\\|");
        if (imgAttr!=null&&imgAttr.length>0){
            Glide.with(context).load(imgAttr[0]).into(viewHolder.iv);

        }else {
            viewHolder.iv.setImageResource(R.mipmap.ic_launcher);
        }

    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_productIcon);
            title = itemView.findViewById(R.id.tv_title);
        }

    }
}
