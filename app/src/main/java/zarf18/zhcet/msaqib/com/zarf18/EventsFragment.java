package zarf18.zhcet.msaqib.com.zarf18;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by MSaqib on 29-03-2018.
 */

public class EventsFragment extends Fragment {

    SwipeRefreshLayout swipeRefreshLayout = null;
    View view;
    CulturalAdapter culturalAdapter;
    TechnicalAdapter technicalAdapter;
    OnlineAdapter onlineAdapter;
    SportsAdapter sportsAdapter;
    LiteraryAdapter literaryAdapter;

    ArrayList<Event>literary = new ArrayList<>();
    ArrayList<Event>cultural = new ArrayList<>();
    ArrayList<Event>technical = new ArrayList<>();
    ArrayList<Event>online = new ArrayList<>();
    ArrayList<Event>sports = new ArrayList<>();
    Context context;
    /*static {
        System.loadLibrary("keys");
    }
    public native String getMainUrl1();
    public native String getMainUrl2();*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_events, container, false);
        context = view.getContext();
        //swipeRefreshLayout = view.findViewById(R.id.event_refresh);
      /*  swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEvents(view);
            }
        });*/
        initUI(view);
        return view;

    }

    private void getEvents() {

        /*String JSON_URL = new String(Base64.decode(getMainUrl1(),Base64.DEFAULT))+Details.selectedSerial.unformatted;
        */
        String JSON_URL = "http://zarfamu.co.in/instagram/insta_api.php?q=events";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /*if(swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);*/

                        try {
                            JSONObject obj = new JSONObject(response);
                          /*  cultural.removeAll(cultural);
                            online.removeAll(online);
                            literary.removeAll(literary);
                            technical.removeAll(technical);
                            sports.removeAll(sports);*/
                            JSONArray data = obj.getJSONArray("data");
                            int count = Integer.parseInt(obj.getString("count"));
                            for(int i=0;i<count;i++){
                               // Log.d("object_found", String.valueOf(data.getJSONObject(i)));
                               // Log.d("object_found2", String.valueOf(data.getJSONObject(i).getString("category")));

                                switch (Integer.parseInt(data.getJSONObject(i).getString("category"))) {
                                    case 4:
                                        technical.add(new Event(data.getJSONObject(i)));
                                        break;
                                    case 1:
                                        literary.add(new Event(data.getJSONObject(i)));
                                        break;
                                    case 2:
                                        online.add(new Event(data.getJSONObject(i)));
                                        break;
                                    case 3:
                                        sports.add(new Event(data.getJSONObject(i)));
                                        break;
                                    default:
                                        cultural.add(new Event(data.getJSONObject(i)));
                                        break;
                                }
                            }
                            //Log.d("sizea",String.valueOf(cultural.size()));
                            culturalAdapter.notifyDataSetChanged();
                            technicalAdapter.notifyDataSetChanged();
                            onlineAdapter.notifyDataSetChanged();
                            literaryAdapter.notifyDataSetChanged();
                            sportsAdapter.notifyDataSetChanged();

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
                        Toast.makeText(getContext(),"Error loading Events", Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void initUI(final View view) {
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(context).inflate(R.layout.item_vp, null, false);

                final RecyclerView recyclerView = view.findViewById(R.id.event_recylerview);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),1,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                culturalAdapter = new CulturalAdapter(getContext(),cultural);
                sportsAdapter = new SportsAdapter(getContext(),sports);
                literaryAdapter = new LiteraryAdapter(getContext(),literary);
                onlineAdapter = new OnlineAdapter(getContext(),online);
                technicalAdapter = new TechnicalAdapter(getContext(),technical);

                switch (position){
                    case 0:
                        recyclerView.setAdapter(culturalAdapter);
                        break;
                    case 1:
                        recyclerView.setAdapter(literaryAdapter);
                        break;
                    case 2:
                        recyclerView.setAdapter(onlineAdapter);
                        break;
                    case 3:
                        recyclerView.setAdapter(sportsAdapter);
                        break;
                    case 4:
                        recyclerView.setAdapter(technicalAdapter);
                        break;
                }

                container.addView(view);
                return  view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) view.findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_theatre_masks),
                        Color.parseColor(colors[0]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_theatre_masks_1))
                        .title("Cultural")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_signature),
                        Color.parseColor(colors[1]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_signature_1))
                        .title("Literary")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_network_support),
                        Color.parseColor(colors[2]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_network_support_1))
                        .title("Online")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_soccer_ball_variant),
                        Color.parseColor(colors[3]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_soccer_ball_variant_1))
                        .title("Sports")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_mechanic_tools),
                        Color.parseColor(colors[4]))
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_mechanic_tools_1))
                        .title("Technical")
                        .badgeTitle("777")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 5);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                culturalAdapter.notifyDataSetChanged();
                technicalAdapter.notifyDataSetChanged();
                onlineAdapter.notifyDataSetChanged();
                literaryAdapter.notifyDataSetChanged();
                sportsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
                culturalAdapter.notifyDataSetChanged();
                technicalAdapter.notifyDataSetChanged();
                onlineAdapter.notifyDataSetChanged();
                literaryAdapter.notifyDataSetChanged();
                sportsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        getEvents();

    }
}
