package zarf18.zhcet.msaqib.com.zarf18;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by MSaqib on 09-04-2017.
 */

public class Member {

    public String name;
    public String post;
    public String url;

    public Member(JSONObject jsonObject) {
        try {

            this.name = jsonObject.getString("name");
            this.post = jsonObject.getString("post");
            this.url = jsonObject.getString("imageUrl");
        } catch (Exception e) {
            Log.d("error in post",e.toString());
        }
    }

}
