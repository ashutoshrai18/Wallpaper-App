package com.ashutosh.wallpapertest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashutosh.wallpapertest.FullViewActivity;
import com.ashutosh.wallpapertest.R;
import com.ashutosh.wallpapertest.model.Model;
import com.ashutosh.wallpapertest.model.Wallpaper_Model;
import com.bumptech.glide.Glide;

import java.util.List;

public class Wallpaper_Adapter extends RecyclerView.Adapter<Wallpaper_AdapterViewHolder>{
    private Context context;
    private List<Model> wallpaper_modelList;

    public Wallpaper_Adapter(Context context, List<Model> wallpaper_modelList) {
        this.context = context;
        this.wallpaper_modelList = wallpaper_modelList;
    }

    @NonNull
    @Override
    public Wallpaper_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpaper_item,parent,false);
        return new Wallpaper_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Wallpaper_AdapterViewHolder holder, int position) {
        Glide.with(context).load(wallpaper_modelList.get(position).getPageURL()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, FullViewActivity.class)
                .putExtra("originalUrl",wallpaper_modelList.get(position).getOriginalURL()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaper_modelList.size();
    }
}

class Wallpaper_AdapterViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    public Wallpaper_AdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageViewItem);

    }
}
