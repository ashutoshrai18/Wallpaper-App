package com.ashutosh.wallpapertest;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashutosh.wallpapertest.adapter.Wallpaper_Adapter;
import com.ashutosh.wallpapertest.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllWallpaperActivity extends AppCompatActivity {


    private List<Model> modelList;
    RecyclerView recyclerView;
    private Wallpaper_Adapter wallpaperAdapter;
    private Boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_wallpaper);

        recyclerView = findViewById(R.id.recyclerView);
        modelList = new ArrayList<>();
        wallpaperAdapter = new Wallpaper_Adapter(this, modelList, false);
        recyclerView.setAdapter(wallpaperAdapter);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }


        });

        API api = new API(this);
//        api.fetchWallpapers();
        api.fetchWallpapers(null, new API.OnWallpapersResponseListener() {
            @Override
            public void onResponse(ArrayList<Model> models) throws JSONException {
                modelList.addAll(models);
                wallpaperAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {
                Toast.makeText(AllWallpaperActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });
        /*api.fetchWallpapers(ma, new API.OnWallpapersResponseListener() {
            @Override
            public void onResponse(ArrayList<Model> models) throws JSONException {
                modelList.addAll(models);
                wallpaperAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int error) {
                Toast.makeText(AllPhotoActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}