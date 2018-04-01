package zarf18.zhcet.msaqib.com.zarf18;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by MSaqib on 30-03-2018.
 */

public class Post {
    private String caption;
    private String likes;
    private String imageUrl;
    private int height;


    public Post(JSONObject jsonObject) {
        try {
            Log.d("obj",jsonObject.toString());
            this.caption = jsonObject.getString("caption");
            this.likes = jsonObject.getString("likes");
            this.imageUrl = jsonObject.getString("imageUrl");
            this.height = Integer.parseInt(jsonObject.getString("height"));
        } catch (Exception e) {
            Log.d("error in post",e.toString());
        }
    }


    public String getLikes() {
        return likes;
    }

    public String getCaption() {
        return caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getHeight() { return height; }
}
