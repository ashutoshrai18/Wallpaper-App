package com.ashutosh.wallpapertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;

public class FullViewActivity extends AppCompatActivity {
    private String originalUrl = "";
    private PhotoView photoView;
    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout linearLayout;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        w.setStatusBarColor(Color.TRANSPARENT);
        w.setNavigationBarColor(Color.TRANSPARENT);
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        setContentView(R.layout.activity_full_view);
//        getSupportActionBar().hide();

        Intent intent = getIntent();
        originalUrl = intent.getStringExtra("originalUrl");
        photoView = findViewById(R.id.photoView);

        Glide.with(this).load(originalUrl).into(photoView);
        init();
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor"})
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                View bgView = findViewById(R.id.bgView);

                if (isChecked) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    bgView.setAlpha(1);
                    bgView.animate().alpha(0.6f).setDuration(500).setStartDelay(300);
                    toggleButton.animate().rotation(180);


                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    bgView.setAlpha(0.6f);
                    bgView.animate().alpha(1).setDuration(300).setStartDelay(0);
                    toggleButton.animate().rotation(0);

                }
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                toggleButton.setChecked(newState == BottomSheetBehavior.STATE_EXPANDED);

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


    public void BottomSheetEvent(View view) {
        init();

    }

    public void SetWallpaperEvent(View view) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        photoView.setDrawingCacheEnabled(true);
//        Bitmap bitmap = ((BitmapDrawable)photoView.getDrawable()).getBitmap();
        Bitmap bitmap = photoView.getDrawingCache();

        try {
            wallpaperManager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper Change Successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void DownloadWallpaperEvent(View view) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(originalUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
        Toast.makeText(this, "Downloading Start", Toast.LENGTH_SHORT).show();

    }

    public void init() {
        this.linearLayout = findViewById(R.id.bottomSheet);
        this.bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        this.toggleButton = findViewById(R.id.toggleButton);

    }
}