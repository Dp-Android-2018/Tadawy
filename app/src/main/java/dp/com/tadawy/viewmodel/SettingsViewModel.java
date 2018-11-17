package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import dp.com.tadawy.R;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.activity.LoginActivity;

public class SettingsViewModel {

    Context context;

    public SettingsViewModel(Context context) {
        this.context = context;
    }

    public void logOut(View view) {

        CustomUtils.getInstance().clearSharedPref(context);
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finishAffinity();
    }

    public void share(View view){
        CustomUtils.getInstance().shareApp(context);
    }

    public void rateApp(View view){
        CustomUtils.getInstance().playStore(context);
    }

    public void dpWebsite(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(context.getString(R.string.website_url)));
        context.startActivity(intent);
    }
}
