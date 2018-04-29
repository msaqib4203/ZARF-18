package zarf18.zhcet.msaqib.com.zarf18;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by MSaqib on 04-04-2018.
 */

public class About extends AppCompatActivity {
    FloatingActionButton web,insta,fb;
    ImageView imageView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        web = findViewById(R.id.zarf_web);
        insta = findViewById(R.id.insta_zarf);
        fb = findViewById(R.id.floatingActionButton);
        imageView = findViewById(R.id.zarf_poster);
        Glide.with(this).load("http://zarfamu.co.in/zarf.jpg").into(imageView);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://zarfamu.co.in"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.instagram.com/zarf18"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.fb.com/zarf.amu"));
                    startActivity(intent);
                }catch(Exception e){}
            }
        });
    }
}
