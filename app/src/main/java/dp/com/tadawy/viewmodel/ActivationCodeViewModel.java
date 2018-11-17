package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.request.CodeRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivationCodeViewModel  {
    Context context;
    BaseInterface callback;
    public ObservableField<String> code;
    public ObservableField<String> phone;
    CodeRequest request;
    public String secondsLeft;

    public ActivationCodeViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        code=new ObservableField<>();
        phone=new ObservableField<>(CustomUtils.getInstance().getSaveUserObject(context).getPhone());
    }

    public void active(View view){
        if(ValidationUtils.isEmpty(code.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
            return;
        }
        request=new CodeRequest(code.get());
        if (NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).activatePhone(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken(),request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("code activation phone : "+defaultResponseResponse.code());
                        if (defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            Intent intent=new Intent(context,ContainerActivity.class);
                            context.startActivity(intent);
                            ((Activity)context).finishAffinity();
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR CODE : "+throwable.getMessage());
                    });
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

}
