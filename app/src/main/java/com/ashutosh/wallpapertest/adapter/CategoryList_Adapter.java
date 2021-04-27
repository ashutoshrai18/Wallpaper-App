package com.ashutosh.wallpapertest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashutosh.wallpapertest.AllPhotoActivity;
import com.ashutosh.wallpapertest.R;
import com.ashutosh.wallpapertest.model.CategoryModelList;
import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryList_Adapter extends RecyclerView.Adapter<CategoryList_Adapter.Category_Adapter_ViewHolder> {
    List<CategoryModelList> modelList;
    Context context;

    public CategoryList_Adapter(List<CategoryModelList> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Category_Adapter_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.thumb_layout, parent, false);
        return new Category_Adapter_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter_ViewHolder holder, int position) {
        Glide.with(context).load(modelList.get(position).getPreviewURL()).error(R.drawable.btn_card).into(holder.imageView);
        holder.textView.setText(modelList.get(position).getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AllPhotoActivity.class)
                        .putExtra("query",modelList.get(position).getName()));
            }
        });
//        Toast.makeText(context, modelList.get(position).getTags(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class Category_Adapter_ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public Category_Adapter_ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCategoryItem);
            textView = itemView.findViewById(R.id.tagsNameTextView);

        }
    }
}
