package dp.com.tadawy.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import dp.com.tadawy.R;
import dp.com.tadawy.application.MyApplication;
import dp.com.tadawy.pojo.model.ConnectionReceiver;
import dp.com.tadawy.utils.NetWorkConnection;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity implements ConnectionReceiver.ConnectionReceiverListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.notification_bar_color));
            checkConnection();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectionListener(this);
    }

    public void checkConnection(){
        if(!NetWorkConnection.isConnectingToInternet(this)){
            Intent intent=new Intent(this,NoInternetConnectionActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(NetWorkConnection.isConnectingToInternet(this)){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finishAffinity();
        }else {
            Intent intent=new Intent(this,NoInternetConnectionActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }
}
