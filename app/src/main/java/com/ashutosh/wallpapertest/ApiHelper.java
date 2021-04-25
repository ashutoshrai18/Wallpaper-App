//package com.ashutosh.wallpapertest;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.ashutosh.wallpapertest.model.Model;
//import com.ashutosh.wallpapertest.model.Wallpaper_Model;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ApiHelper {
//    public static final String URL = "https://api.pexels.com/v1/curated/";
//
//
//    public static void getMoreImages(Context context, int page, APIResponse apiResponse){
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        StringRequest request = new StringRequest(Request.Method.GET, "https://pixabay.com/api/?key=21200728-66e656db38c1b714199b8e589", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    JSONArray array = object.getJSONArray("hits");
//                    ArrayList<Model> arrayList = new ArrayList<>();
//                    int iss = array.length();
//                    for (int i = 0; i < 20; i++) {
//                        JSONObject eachPhoto = array.getJSONObject(i);
//                        String imageURL = eachPhoto.getString("previewURL");
////                        Model model = new Model(eachPhoto.getInt("id"), eachPhoto.getString("previewURL"));
//                        arrayList.add(model);
//                        if (apiResponse != null)
//                            apiResponse.onResponse(arrayList);
//                    }
//
////                    Log.d("tagtag", object.toString(5));
//                } catch (JSONException e) {
//                    Log.d("tagtag", response);
//                    Log.e("tagtag", e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        queue.add(request);
//    }
//
//    public interface APIResponse {
//        void onResponse(ArrayList<Model> list);
//    }
//}
