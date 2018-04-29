package zarf18.zhcet.msaqib.com.zarf18;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by MSaqib on 04-04-2018.
 */



public class Developer extends AppCompatActivity {
    private FloatingActionButton fbBtn,lnkBtn,gitBtn;
    CircleImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fbBtn = findViewById(R.id.dev_facebook);
        lnkBtn = findViewById(R.id.dev_linkedin);
        gitBtn = findViewById(R.id.dev_github);
        imageView = findViewById(R.id.imageView_dev);
        Glide.with(this).load("http://zarfamu.co.in/pages/event/member_logo/33.jpg").into(imageView);

        lnkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.linkedin.com/in/msaqib4203/"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://fb.com/mohammadsaqib.ahmed"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });

        gitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.github.com/msaqib4203/"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });
    }
}
