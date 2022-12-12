package com.ashutosh.wallpapertest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashutosh.wallpapertest.model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API {

    private final String url = "https://pixabay.com/api/";
    private final RequestQueue queue;

    public API(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    private interface OnAPIResponseListener {
        void onResponse(JSONObject object) throws JSONException;

        void onError(int error);
    }

    public interface OnWallpapersResponseListener {
        void onResponse(ArrayList<Model> models) throws JSONException;

        void onError(int error);
    }

    public void fetchWallpapers(Map<String, String> map, @NonNull OnWallpapersResponseListener onWallpapersResponseListener) {
        fetchData(map, new OnAPIResponseListener() {
            @Override
            public void onResponse(JSONObject object) throws JSONException {
                ArrayList<Model> models = new ArrayList<>();
                JSONArray array = object.getJSONArray("hits");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject eachPhoto = array.getJSONObject(i);
//                    String imageTags = eachPhoto.getString("tags");
                    Model model = new Model(eachPhoto.getInt("id"), eachPhoto.getString("webformatURL"), eachPhoto.getString("largeImageURL"));
                    models.add(model);
                }
                onWallpapersResponseListener.onResponse(models);
            }

            @Override
            public void onError(int error) {
                onWallpapersResponseListener.onError(error);
            }
        });
    }

    private void fetchData(Map<String, String> map, @NonNull OnAPIResponseListener onAPIResponseListener) {


        StringBuilder defaultGet = new StringBuilder("?key=21200728-66e656db38c1b714199b8e589&per_page=16&safesearch=true");
        if (map != null)
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String qp = "&" + entry.getKey() + "=" + entry.getValue();
                defaultGet.append(qp);
            }
        StringRequest request = new StringRequest(Request.Method.GET, url + defaultGet.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    onAPIResponseListener.onResponse(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                    onAPIResponseListener.onError(898);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onAPIResponseListener.onError(error.networkResponse.statusCode);
                Log.e("vgt", error.getMessage());
            }
        });
        queue.add(request);
    }
}
