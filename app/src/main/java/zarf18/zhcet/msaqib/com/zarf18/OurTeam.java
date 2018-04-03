package zarf18.zhcet.msaqib.com.zarf18;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MSaqib on 09-04-2017.
 */

public class OurTeam extends AppCompatActivity {
    static ArrayList<Member> members = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Context context  = this;
    SwipeRefreshLayout swipeRefreshLayout;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ourteam);
        swipeRefreshLayout = findViewById(R.id.team_refresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),1,false));
        myAdapter = new MyAdapter(OurTeam.this,members);
        recyclerView.setAdapter(myAdapter);
        fetchTeam();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTeam();
            }
        });
    }

    private void fetchTeam() {

        /*String JSON_URL = new String(Base64.decode(getMainUrl1(),Base64.DEFAULT))+Details.selectedSerial.unformatted;
        */
        String JSON_URL = "http://zarfamu.co.in/instagram/insta_api.php?q=team";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);
                        try {

                            JSONObject obj = new JSONObject(response);
                            members.removeAll(members);
                            JSONArray data = obj.getJSONArray("data");
                            int count = Integer.parseInt(obj.getString("count"));
                            for(int i=0;i<count;i++){
                                Log.d("object", String.valueOf(data.getJSONObject(i)));
                                members.add(new Member(data.getJSONObject(i)));
                            }

                            myAdapter.notifyDataSetChanged();

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
                        Toast.makeText((context),"Error loading", Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}

