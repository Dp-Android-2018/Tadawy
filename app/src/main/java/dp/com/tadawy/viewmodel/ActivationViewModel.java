package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.ActivationActivity;
import dp.com.tadawy.view.activity.PhoneActivationActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivationViewModel {
    Context context;
    BaseInterface callback;

    public ActivationViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
    }

    public void phoneActivation(View view){
        Intent intent=new Intent(context,PhoneActivationActivity.class);
        context.startActivity(intent);
    }

    public void sendMail(View view){
        if (NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).sendActivationMail(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Send activitation mail code : "+defaultResponseResponse.code());
                        if (defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE);
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Error send mail : "+throwable.getMessage());
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
    public void back(View view){
        ((Activity)context).finish();
    }
}
