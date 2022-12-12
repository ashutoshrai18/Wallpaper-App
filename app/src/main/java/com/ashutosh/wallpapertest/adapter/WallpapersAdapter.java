package com.ashutosh.wallpapertest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashutosh.wallpapertest.model.Model;

import java.util.List;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.WallpaperAdapterViewHolder> {
    private Context context;
    private List<Model> wallpaper_modelList;

    public WallpapersAdapter(Context context, List<Model> wallpaper_modelList) {
        this.context = context;
        this.wallpaper_modelList = wallpaper_modelList;
    }

    @NonNull
    @Override
    public WallpaperAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class WallpaperAdapterViewHolder extends RecyclerView.ViewHolder {

        public WallpaperAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
