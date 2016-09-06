package kizema.anton.animateviewshowcase;

import android.app.Application;
import android.content.Context;

import kizema.anton.animateviewshowcase.helpers.UIHelper;

/**
 * Created by somename on 06.09.2016.
 */
public class App extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        UIHelper.init(appContext);
    }

    public static Context getAppContext(){
        return appContext;
    }

}
