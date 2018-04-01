package zarf18.zhcet.msaqib.com.zarf18;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MSaqib on 29-03-2018.
 */

public class FeedsFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout = null;
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PostAdapter postAdapter;
    ArrayList<Post> posts = new ArrayList<>();

    /*static {
        System.loadLibrary("keys");
    }
    public native String getMainUrl1();
    public native String getMainUrl2();*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_feeds, container, false);
        /*expandingList = view.findViewById(R.id.expanding_list_main);*/
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext(),1,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter = new PostAdapter(getContext(),posts);
        recyclerView.setAdapter(postAdapter);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPosts();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        getPosts();
        return view;

    }

    public void setOnClick(final View view, final String ur, final  String tag,final String seasonName){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(view.getContext(),String.valueOf(tag.length()),Toast.LENGTH_SHORT).show();
              /* Intent intent = new Intent(view.getContext(),Player.class);
                intent.putExtra("url",ur);
                intent.putExtra("seasonName",seasonName);
                intent.putExtra("tag",tag);
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext());
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,seasonName+" "+tag);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                startActivity(intent);*/
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(view.getContext());
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,seasonName+" "+tag);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                /*if(ur!=null){
                    GiraffePlayer.play(view.getContext(),new VideoInfo(Uri.parse(ur)));
                }
                else
                    Toast.makeText(view.getContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();*/
            }
        });
    }

    private void getPosts() {

        /*String JSON_URL = new String(Base64.decode(getMainUrl1(),Base64.DEFAULT))+Details.selectedSerial.unformatted;
        */
        String JSON_URL = "http://zarfamu.co.in/instagram/insta_api.php?q=recent";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    public int i,j;
                    @Override
                    public void onResponse(String response) {
                       if(swipeRefreshLayout.isRefreshing())
                           swipeRefreshLayout.setRefreshing(false);

                        try {
                            Log.d("respons",response.toString());
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            posts.removeAll(posts);
                            JSONArray data = obj.getJSONArray("data");
                            int count = Integer.parseInt(obj.getString("count"));
                            for(int i=0;i<count;i++){
                                Log.d("object", String.valueOf(data.getJSONObject(i)));
                                posts.add(new Post(data.getJSONObject(i)));
                            }

                            postAdapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if(swipeRefreshLayout!=null && swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(view.getContext(),"Error loading", Toast.LENGTH_SHORT).show();
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
