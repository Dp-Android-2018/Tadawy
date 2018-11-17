package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.request.LoginRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.SharedPrefrenceUtils;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ActivationActivity;
import dp.com.tadawy.view.activity.ConnectTeamWorkActivity;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.activity.ForgetPasswordActivity;
import dp.com.tadawy.view.activity.MainActivity;
import dp.com.tadawy.view.activity.RequestActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel {
    Context context;
    BaseInterface callBack;
    public ObservableField<String> mail;
    public ObservableField<String> password;
    LoginRequest loginRequest;
    private SharedPrefrenceUtils pref;

    public LoginViewModel(Context context,BaseInterface callBack) {
        this.context = context;
        this.callBack=callBack;
        initVariables();
    }

    public void initVariables(){
        mail=new ObservableField<>();
        password=new ObservableField<>();
        loginRequest=new LoginRequest();
    }

    public void checkData(View view) {
        if (ValidationUtils.isEmpty(mail.get()) ||
                ValidationUtils.isEmpty(password.get())) {
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else {
            if(!ValidationUtils.isMail(mail.get())){
                callBack.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
            }else{
                loginRequest.setEmail(mail.get());
                loginRequest.setPassword(password.get());
                login();
            }
        }
    }

    public void login(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).login(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,loginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("COde in log in is :"+loginResponseResponse.code());
                        switch (loginResponseResponse.code()){
                            case (ConfigurationFile.Constants.SUCCESS_CODE):
                            {
                                saveDataToPrefs(loginResponseResponse.body().getLoginResponseContent());
                                System.out.println("role  is : "+CustomUtils.getInstance().getSaveUserObject(context).getRole());
                                System.out.println("data saved to pref is : "+CustomUtils.getInstance().getSaveUserObject(context));
                                if(CustomUtils.getInstance().getSaveUserObject(context).getRole()
                                        .equals(ConfigurationFile.Constants.COMPANY)){
                                    if (CustomUtils.getInstance().getSaveUserObject(context).isStatus()){
                                        Intent i = new Intent(context, RequestActivity.class);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }else {
                                        Intent i = new Intent(context, ConnectTeamWorkActivity.class);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }

                                }else if(CustomUtils.getInstance().getSaveUserObject(context).getRole()
                                        .equals(ConfigurationFile.Constants.CLIENT)) {
                                    if (CustomUtils.getInstance().getSaveUserObject(context).isStatus()){
                                        Intent i = new Intent(context, ContainerActivity.class);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }else {
                                        Intent i = new Intent(context, ActivationActivity.class);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }

                                }
                                break;
                            }
                            case (ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD):
                            {
                                callBack.updateUi(ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD);
                                break;
                            }
                            case (ConfigurationFile.Constants.INVALED_DATA_CODE):
                            {
                                callBack.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                                break;
                            }
                            case (ConfigurationFile.Constants.SUSBENDED):
                            {
                                callBack.updateUi(ConfigurationFile.Constants.SUSBENDED);
                                break;
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR login :"+throwable.getMessage());
                    });

        }else{
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME,data);
    }

    public void forgetPassword(View view){
        Intent intent=new Intent(context,ForgetPasswordActivity.class);
        context.startActivity(intent);
    }
}
