package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.google.android.gms.common.api.Api;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.request.CheckPhoneRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.ActivationCodeActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PhoneActivationViewModel  {
    public ObservableField<String> phone;
    Context context;
    BaseInterface callback;
    CheckPhoneRequest request;
    public int secondsLeft;

    public PhoneActivationViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        phone=new ObservableField<>(CustomUtils.getInstance().getSaveUserObject(context).getPhone());
        request=new CheckPhoneRequest(phone.get());
    }

    public void sendCode(View view){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).sendActivationCode(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken(),request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code Activation : "+defaultResponseResponse.code());
                        if (defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){

                            Intent intent=new Intent(context,ActivationCodeActivity.class);
                            context.startActivity(intent);
                        }
                        if(defaultResponseResponse.code()==ConfigurationFile.Constants.TRY_LATER){
                            //secondsLeft=defaultResponseResponse.body().getSecondsLeft();
                            callback.updateUi(ConfigurationFile.Constants.TRY_LATER);
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR code activation : "+throwable.getMessage());
                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
