package com.aka.mybox.Sample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aka.mybox.R;
import com.aka.mybox.Sample.Gank.modle.GankBean;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aka on 2018/11/12
 */
public class GankListAdapter extends RecyclerView.Adapter {
    List<GankBean> gankBeans;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent == null) return null;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        GankBean gankBean = gankBeans.get(position);
        if (gankBean != null) {
            Glide.with(holder.itemView.getContext())
                    .load(gankBean.getUrl())
                    .into(debounceViewHolder.imageIv);
            debounceViewHolder.descriptionTx.setText(gankBean.getDesc());
        }
    }

    @Override
    public int getItemCount() {
        return gankBeans == null ? 0 : gankBeans.size();
    }

    public void setImages(List<GankBean> gankBeans) {
        this.gankBeans = gankBeans;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.grid_imageIv)
        ImageView imageIv;
        @BindView(R.id.grid_descriptionTv)
        TextView descriptionTx;

        public DebounceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
