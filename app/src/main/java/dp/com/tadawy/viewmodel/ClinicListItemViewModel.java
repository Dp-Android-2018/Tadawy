package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.view.View;

import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.activity.DetailedActivity;

public class ClinicListItemViewModel {
   private LoginResponseContent data;
   private Context context;
   public ObservableFloat rate;

    public ClinicListItemViewModel(LoginResponseContent data,Context context) {
        this.data = data;
        this.context=context;
        rate=new ObservableFloat(data.getAverageRating());
    }

    public void setData(LoginResponseContent data) {
        this.data = data;
    }

    public String getName(){
        return data.getName();
    }

    public String getDistance(){
        return String.valueOf(data.getDistance());
    }

    public String getDescription(){
        return data.getMetaData().getDescription();
    }

    public String getLogo(){
        return data.getLogo_url();
    }

    public void onItemClick(View view){
        Intent intent = new Intent(context, DetailedActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,data);
        context.startActivity(intent);
    }
}
