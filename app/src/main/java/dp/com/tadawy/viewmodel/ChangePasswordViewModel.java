package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.request.ChangePasswordRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ActivationCodeActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChangePasswordViewModel {
    Context context;
    BaseInterface callBack;
    public ObservableField<String>oldPassword;
    public ObservableField<String>newPassword;
    public ObservableField<String>passwordConfirmation;
    ChangePasswordRequest request;

    public ChangePasswordViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        oldPassword=new ObservableField<>();
        newPassword=new ObservableField<>();
        passwordConfirmation=new ObservableField<>();
    }

    public void back(View view){
        ((Activity)context).finish();
    }

    public void checkData(View view){
        if(ValidationUtils.isEmpty(oldPassword.get())||
           ValidationUtils.isEmpty(newPassword.get())||
           ValidationUtils.isEmpty(passwordConfirmation.get())){
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else if(newPassword.get().length()<8) {
            callBack.updateUi(ConfigurationFile.Constants.PASSWORD_LENGTH_ERROR);
        }else if (!newPassword.get().equals(passwordConfirmation.get())){
            callBack.updateUi(ConfigurationFile.Constants.PASSWORD_CONFIRMATION_ERROR);
        }else {
            request=new ChangePasswordRequest(oldPassword.get(),newPassword.get(),passwordConfirmation.get());
            confirm();
        }
    }

    public void confirm(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).changePassword(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken(),request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("change password code : "+defaultResponseResponse.code());
                        callBack.updateUi(defaultResponseResponse.code());

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR CHANGE PASSWORD : "+throwable.getMessage());

                    });

        }else {
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
