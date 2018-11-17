package dp.com.tadawy.application;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import dp.com.tadawy.R;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.pojo.model.ConnectionReceiver;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    private static List<City> cities=new ArrayList<>();

    public static List<City> getCities() {
        return cities;
    }

    public static void setCities(List<City> cities) {
        MyApplication.cities = cities;
    }

    public void onCreate(){
        super.onCreate();
        mInstance=this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setFontAttrId(R.font.arabic_font)
                .build()
        );
    }

    public static synchronized MyApplication getInstance() {
        if(mInstance==null){
            mInstance=new MyApplication();
        }
        return mInstance;
    }

    public void setConnectionListener(ConnectionReceiver.ConnectionReceiverListener listener) {
        ConnectionReceiver.connectionReceiverListener = listener;
    }

}
