package zarf18.zhcet.msaqib.com.zarf18;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by MSaqib on 03-04-2018.
 */

public class Event {

    public String name;
    public String descripton;
    public String organiser1;
    public String organiser2;
    public String organiser3;
    public String organiser4;
    public String date;
    public String fees;
    public String team;

    public Event(JSONObject jsonObject) {
        try {

            this.name = jsonObject.getString("name");
            this.descripton = jsonObject.getString("description");
            this.organiser1 = jsonObject.getString("organiser1");
            this.organiser2 = jsonObject.getString("organiser2");
            this.organiser3 = jsonObject.getString("organiser3");
            this.organiser4 = jsonObject.getString("organiser4");
            this.date = jsonObject.getString("date");
            this.fees = jsonObject.getString("fees");
            this.team = jsonObject.getString("team");
            Log.d("sucv","sucv");
        } catch (Exception e) {
            Log.d("error in post",e.toString());
        }
    }

}
