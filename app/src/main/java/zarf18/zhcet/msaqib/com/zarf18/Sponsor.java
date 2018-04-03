package zarf18.zhcet.msaqib.com.zarf18;

/**
 * Created by MSaqib on 03-04-2018.
 */

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by MSaqib on 09-04-2017.
 */

public class Sponsor {

    public String name;
    public String category;
    public String url;

    public Sponsor(JSONObject jsonObject) {
        try {

            this.name = jsonObject.getString("name");
            this.category = jsonObject.getString("type");
            this.url = jsonObject.getString("imageUrl");
        } catch (Exception e) {
            Log.d("error in post",e.toString());
        }
    }

}
