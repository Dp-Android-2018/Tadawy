package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.activity.ChangePasswordActivity;

public class ProfileViewModel {
    Context context;
    public ObservableField<String>name;
    public ObservableField<String>mail;
    public ObservableField<String>phone;
    public ObservableField<String>country;
    public ObservableField<String>city;
    LoginResponseContent loginResponseContent;

    public ProfileViewModel(Context context) {
        this.context = context;
        loginResponseContent=CustomUtils.getInstance().getSaveUserObject(context);
        initVariables();
    }
    public void initVariables(){
        name=new ObservableField<>(loginResponseContent.getName());
        mail=new ObservableField<>(loginResponseContent.getEmail());
        phone=new ObservableField<>(loginResponseContent.getPhone());
        country=new ObservableField<>(loginResponseContent.getCountry().getName());
        city=new ObservableField<>(loginResponseContent.getCity().getName());
    }

    public void changePassword(View view){
        Intent intent=new Intent(context,ChangePasswordActivity.class);
        context.startActivity(intent);
    }

}
