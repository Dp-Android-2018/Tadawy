package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.request.CheckMailRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ForgetPasswordViewModel {
    Context context;
    BaseInterface callback;
    public ObservableField<String> mail;
    CheckMailRequest request;

    public ForgetPasswordViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        mail=new ObservableField<>();
    }
    public void send(View view){
        if(ValidationUtils.isEmpty(mail.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else if(NetWorkConnection.isConnectingToInternet(context)){
            request=new CheckMailRequest(mail.get());
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).forgetPassword(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Forget Password code : "+defaultResponseResponse.code());
                        callback.updateUi(defaultResponseResponse.code());
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR forget password : "+throwable.getMessage());
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }


}
