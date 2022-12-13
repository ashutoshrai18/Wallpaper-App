package com.ashutosh.wallpapertest.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ashutosh.wallpapertest.AllPhotoActivity;
import com.ashutosh.wallpapertest.R;
import com.ashutosh.wallpapertest.model.CategoryModelList;
import com.ashutosh.wallpapertest.model.ColorsModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.Category_Adapter_ViewHolder> {
    List<ColorsModel> modelList;
    Context context;

    public ColorsAdapter(List<ColorsModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_Adapter_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.colors_layout, parent, false);
        return new Category_Adapter_ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Category_Adapter_ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setBackgroundColor(modelList.get(position).getColor());
//        holder.imageView.setBackground();
        Toast.makeText(context, "" + modelList.get(position).getColor(), Toast.LENGTH_SHORT).show();
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AllPhotoActivity.class)
                        .putExtra("query",modelList.get(position).getColorName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class Category_Adapter_ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        CardView cardView;
        TextView textView;
        public Category_Adapter_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewItem);

        }
    }
}
