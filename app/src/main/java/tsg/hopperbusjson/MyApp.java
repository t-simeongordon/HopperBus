package tsg.hopperbusjson;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by terrelsimeongordon on 15/04/16.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}