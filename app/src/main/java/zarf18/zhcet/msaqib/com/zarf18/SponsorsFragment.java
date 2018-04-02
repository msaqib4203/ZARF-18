package zarf18.zhcet.msaqib.com.zarf18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import quatja.com.vorolay.VoronoiView;

/**
 * Created by MSaqib on 01-04-2018.
 */

public class SponsorsFragment extends Fragment{

    SwipeRefreshLayout swipeRefreshLayout=null;
    VoronoiView voronoiView;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        voronoiView = view.findViewById(R.id.sponsors_collage);
        View xview = getLayoutInflater().inflate(R.layout.item_sponsor, null, false);
        voronoiView.addView(xview);
        getImages();
        return view;
    }


    private void getImages() {

        /*String JSON_URL = new String(Base64.decode(getMainUrl1(),Base64.DEFAULT))+Details.selectedSerial.unformatted;
        */
        String JSON_URL = "http://zarfamu.co.in/instagram/insta_api.php?q=collage";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*if(swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);*/

                        try {
                            Toast.makeText(getContext(),"OK loading collage", Toast.LENGTH_SHORT).show();
                            Log.d("respons",response.toString());
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            JSONArray data = obj.getJSONArray("data");
                            int count = Integer.parseInt(obj.getString("count"));
                            LayoutInflater layoutInflater = getLayoutInflater();
                            if(count>25)
                                count=25;
                            for (int i = 0; i < count; i++) {
                                View view = layoutInflater.inflate(R.layout.item_sponsor, null, false);
                                voronoiView.addView(view);
                                //ImageView imageView = view.findViewById(R.id.collage_imageview);
                                ImageView layout = (ImageView) view.findViewById(R.id.layout);
                                // Glide.with(getContext()).load(posts.get(position).getImageUrl()).into(imageView);
                                Glide.with(getContext()).load(data.getJSONObject(i).getString("imageUrl")).into(layout);
                                //layout.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                            }
                            voronoiView.refresh();
                            /*voronoiView.setOnRegionClickListener(new VoronoiView.OnRegionClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    Toast.makeText(getContext(), "position: " + position, Toast.LENGTH_SHORT).show();
                                }
                            });*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                       /* if(swipeRefreshLayout!=null && swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);*/
                        Toast.makeText(getContext(),"Error loading collage", Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
