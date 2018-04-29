package zarf18.zhcet.msaqib.com.zarf18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by MSaqib on 03-04-2018.
 */

public class TechnicalAdapter extends RecyclerView.Adapter<TechnicalAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> events;
    LayoutInflater inflater;
    AlertDialog.Builder builder;

    public TechnicalAdapter(Context context, ArrayList<Event> events) {

        this.context = context;
        this.events = events;
        inflater = LayoutInflater.from(context);
        builder = new AlertDialog.Builder(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View view = inflater.inflate(R.layout.event, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int position) {

        myViewHolder.title.setText(events.get(position).name);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setView(inflater.inflate(R.layout.event_details,null));
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                ((AutofitTextView)dialog.findViewById(R.id.event_title)).setText(events.get(position).name);
                ((TextView)dialog.findViewById(R.id.event_description)).setText(events.get(position).descripton);
                ((TextView)dialog.findViewById(R.id.event_fee)).setText(events.get(position).fees);
                ((TextView)dialog.findViewById(R.id.event_date)).setText(events.get(position).date);
                ((TextView)dialog.findViewById(R.id.event_team)).setText(events.get(position).team);
                ((TextView)dialog.findViewById(R.id.organiser1)).setText(events.get(position).organiser1);
                if(events.get(position).organiser2.equals("NA"))
                    ((TextView)dialog.findViewById(R.id.organiser2)).setVisibility(View.GONE);
                else
                    ((TextView)dialog.findViewById(R.id.organiser2)).setText(events.get(position).organiser2);

                if(events.get(position).organiser3.equals("NA"))
                    ((TextView)dialog.findViewById(R.id.organiser3)).setVisibility(View.GONE);
                else
                    ((TextView)dialog.findViewById(R.id.organiser3)).setText(events.get(position).organiser3);

                if(events.get(position).organiser4.equals("NA"))
                    ((TextView)dialog.findViewById(R.id.organiser4)).setVisibility(View.GONE);
                else
                    ((TextView)dialog.findViewById(R.id.organiser4)).setText(events.get(position).organiser4);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        AutofitTextView title;

        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.event_title);
            cardView = itemView.findViewById(R.id.event_Card);

        }
    }
}