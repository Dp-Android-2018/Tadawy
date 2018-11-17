package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import dp.com.tadawy.view.activity.ClientRegisterStep1Activity;
import dp.com.tadawy.view.activity.CompanyRegisterStep1Activity;

public class SignupViewModel {
    Context context;

    public SignupViewModel(Context context) {
        this.context = context;
    }

    public void userRegister(View view){
        Intent i=new Intent(context, ClientRegisterStep1Activity.class);
        context.startActivity(i);
        //((Activity)context).finishAffinity();
    }
    public void companyRegister(View view){
        Intent i=new Intent(context, CompanyRegisterStep1Activity.class);
        context.startActivity(i);
        //((Activity)context).finishAffinity();
    }

}
