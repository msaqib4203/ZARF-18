package zarf18.zhcet.msaqib.com.zarf18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.felix.imagezoom.ImageZoom;

/**
 * Created by MSaqib on 07-02-2018.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Post> posts;
    private LayoutInflater inflater;

    PostAdapter(Context context, ArrayList<Post> info) {
        this.context = context;
        this.posts = info;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = inflater.inflate(R.layout.post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {

        myViewHolder.imageView.getLayoutParams().height=posts.get(position).getHeight();
        myViewHolder.caption.setText(posts.get(position).getCaption());
        myViewHolder.likes.setText(posts.get(position).getLikes()+ " likes");
        Glide.with(context).load(posts.get(position).getImageUrl()).into(myViewHolder.imageView);
       /* myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse("https://www.instagram.com/zarf18/"));
                    //Toast.makeText(this,"Feature not available!",Toast.LENGTH_SHORT).show();
                    context.startActivity(intent1);
                } catch(Exception e) {}
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView caption;
        ImageZoom imageView;
        Context context;
        TextView likes;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
          //  cardView = itemView.findViewById(R.id.serial_card);
            caption = itemView.findViewById(R.id.postCaption);
            imageView = itemView.findViewById(R.id.postImage);
            likes = itemView.findViewById(R.id.postLikes);
            cardView = itemView.findViewById(R.id.postCard);
            context = itemView.getContext();
        }
    }
}
