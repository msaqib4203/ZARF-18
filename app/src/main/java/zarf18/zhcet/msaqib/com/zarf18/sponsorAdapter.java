package zarf18.zhcet.msaqib.com.zarf18;

/**
 * Created by MSaqib on 03-04-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by MSaqib on 26-10-2016.
 */
public class sponsorAdapter extends RecyclerView.Adapter<sponsorAdapter.MyViewHolder> {

    Context context;
    ArrayList<Sponsor> members;
    LayoutInflater inflater;

    public sponsorAdapter(Context context, ArrayList<Sponsor> members) {

        this.context = context;
        this.members = members;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View view = inflater.inflate(R.layout.sponsor, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

        myViewHolder.title.setText(members.get(position).name);
        myViewHolder.cat.setText(members.get(position).category);
        try {
            Glide.with(context).load(members.get(position).url).into(myViewHolder.photo);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView cat;
        ImageView photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.sponsor_name);
            cat = (TextView) itemView.findViewById(R.id.sponsor_type);
            photo = (ImageView) itemView.findViewById(R.id.sponsor_logo);
        }
    }
}
