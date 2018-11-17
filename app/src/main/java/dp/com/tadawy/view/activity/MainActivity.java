package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import dp.com.tadawy.R;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            if(NetWorkConnection.isConnectingToInternet(this)){
                if(CustomUtils.getInstance().getSaveUserObject(this)!=null){
                    if(CustomUtils.getInstance().getSaveUserObject(this).getRole()
                            .equals(ConfigurationFile.Constants.COMPANY)){
                        if (CustomUtils.getInstance().getSaveUserObject(this).isStatus()){
                            Intent i = new Intent(MainActivity.this, RequestActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Intent i = new Intent(MainActivity.this, ConnectTeamWorkActivity.class);
                            startActivity(i);
                            finish();
                        }

                    }else if(CustomUtils.getInstance().getSaveUserObject(this).getRole()
                            .equals(ConfigurationFile.Constants.CLIENT)) {
                        if (CustomUtils.getInstance().getSaveUserObject(this).isStatus()){
                            Intent i = new Intent(MainActivity.this, ContainerActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Intent i = new Intent(MainActivity.this, ActivationActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                }else {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }else{
                Snackbar.make((findViewById(R.id.cl_root)),R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
            }
        },4000);

    }
}
