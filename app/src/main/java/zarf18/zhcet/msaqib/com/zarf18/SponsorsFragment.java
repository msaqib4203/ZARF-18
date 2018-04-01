package zarf18.zhcet.msaqib.com.zarf18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by MSaqib on 01-04-2018.
 */

public class SponsorsFragment extends Fragment{

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        /*expandingList = view.findViewById(R.id.expanding_list_main);*/
        /*recyclerView = view.findViewById(R.id.recyclerView);
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
        swipeRefreshLayout.setRefreshing(true);*/
        return view;

    }
}
