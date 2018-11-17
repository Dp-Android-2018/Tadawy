package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.request.CreateReservationRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.activity.DetailedActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AnthorPatientViewModel {

    public ObservableField<String> name;
    public ObservableField<String> mail;
    public ObservableField<String> phone;
    public ObservableField<String> clinicName;
    private Context context;
    private CreateReservationRequest request;
    private BaseInterface callback;
    private LoginResponseContent data;
    private String token="Bearer ";

    public AnthorPatientViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        token+=CustomUtils.getInstance().getSaveUserObject(context).getToken();
        data=(LoginResponseContent)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        initVariables();

    }
    public void initVariables(){
        name=new ObservableField<>();
        mail=new ObservableField<>();
        phone=new ObservableField<>();
        clinicName=new ObservableField<>(data.getName());
        request=new CreateReservationRequest();
    }
    public void cancelReques(View view){
        Intent intent=new Intent(context,DetailedActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,data);
        context.startActivity(intent);
        ((Activity)context).finishAffinity();
    }

    public void checkEmpatData(View view){
        if(ValidationUtils.isEmpty(name.get())||
           ValidationUtils.isEmpty(mail.get())||
           ValidationUtils.isEmpty(phone.get())){
            callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else if(!ValidationUtils.isMail(mail.get())){
            callback.updateUi(ConfigurationFile.Constants.INVALED_EMAIL);
        }else {
            request.setCompanyId(data.getId());
            request.setName(name.get());
            request.setEmail(mail.get());
            request.setPhone(phone.get());
            createReservation();
        }
    }

    public void createReservation(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).createReservation(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<DefaultResponse>>() {
                        @Override
                        public void accept(Response<DefaultResponse> defaultResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("create reservation code : "+defaultResponseResponse.code());
                            Intent intent=new Intent(context,ContainerActivity.class);
                            intent.putExtra(ConfigurationFile.IntentConstants.CONTAINER_RESERVATION,1);
                            context.startActivity(intent);
                            ((Activity)context).finishAffinity();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("ERROR CREATE RESERVATION ANTHOR PATIENT :"+throwable.getMessage());
                        }
                    });

        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
