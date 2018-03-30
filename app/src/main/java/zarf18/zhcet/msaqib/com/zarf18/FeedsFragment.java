package zarf18.zhcet.msaqib.com.zarf18;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by MSaqib on 29-03-2018.
 */

public class FeedsFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout = null;
    View view;

    /*static {
        System.loadLibrary("keys");
    }
    public native String getMainUrl1();
    public native String getMainUrl2();*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_feeds, container, false);
        /*expandingList = view.findViewById(R.id.expanding_list_main);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEpisodes();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        getEpisodes();*/
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

    private void getEpisodes() {

        /*String JSON_URL = new String(Base64.decode(getMainUrl1(),Base64.DEFAULT))+Details.selectedSerial.unformatted;
        //creating a string request to send request to the url
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
                            seasons.removeAll(seasons);
                            expandingList.removeAllViews();
                            int sCount = Integer.parseInt(String.valueOf(obj.get("sCount")));
                            JSONArray seasonsJson = obj.getJSONArray("data");
                            Season newSeason;
                            for(int i=0;i<sCount;i++){
                               newSeason = new Season(seasonsJson.getJSONObject(i));
                               if(newSeason.isValid())
                                   seasons.add(newSeason);
                            }
                            Collections.sort(seasons,seasonComparator);
                            TextView tv;
                            for(i=0;i<seasons.size();i++){
                                item = expandingList.createNewItem(R.layout.episodesplusseason);
                                item.setIndicatorColorRes(R.color.blue);
                                item.setIndicatorIconRes(R.drawable.ic_tv_black_24dp);
                                tv = item.findViewById(R.id.title);
                                tv.setText("Season "+seasons.get(i).number);
                                item.createSubItems(seasons.get(i).eCount);
                                View subIthitem;

                                for(j=0;j<seasons.get(i).eCount;j++){

                                    subIthitem = item.getSubItemView(j);
                                    String ur = new String(Base64.decode(getMainUrl2(),Base64.DEFAULT))+seasons.get(i).heading+seasons.get(i).episodes.get(j).link;
                                    setOnClick(subIthitem,ur,seasons.get(i).episodes.get(j).tag,Details.selectedSerial.text);
                                    ((TextView) subIthitem.findViewById(R.id.sub_title)).setText(seasons.get(i).episodes.get(j).tag);
                                    ((TextView) subIthitem.findViewById(R.id.sub_size)).setText(seasons.get(i).episodes.get(j).quality);
                                }
                            }

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
        requestQueue.add(stringRequest);*/
    }
}
