package bw.com.zhoukao1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import bw.com.zhoukao1.R;
import bw.com.zhoukao1.entivity.UserBean;

public class MAdapter extends RecyclerView.Adapter<MAdapter.ViewHolder>{
    private Context context;
    private List<UserBean.DataBean.BannerBean> list;

    public MAdapter(Context context, List<UserBean.DataBean.BannerBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<UserBean.DataBean.BannerBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.text.setText(list.get(i).getTitle());
        String string = ImageLoaderConfiguration.createDefault(context).toString();
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(build);
        ImageLoader.getInstance().displayImage(list.get(i).getUrl(),viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.text);
            this.image = itemView.findViewById(R.id.image);
        }
    }
}
