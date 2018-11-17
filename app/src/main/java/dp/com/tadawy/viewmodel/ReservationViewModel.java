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
import dp.com.tadawy.view.activity.AnthorPatientActivity;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.activity.DetailedActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ReservationViewModel {
    private LoginResponseContent userData;
    private Context context;
    BaseInterface callback;
    private String token="Bearer ";
    public ObservableField<String> clinicName;
    private LoginResponseContent clinicData;
    private CreateReservationRequest request;

    public ReservationViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        request=new CreateReservationRequest();
        userData=(LoginResponseContent)CustomUtils.getInstance().getSaveUserObject(context);
        clinicData=(LoginResponseContent)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        clinicName=new ObservableField<>(clinicData.getName());
        token+=userData.getToken();
    }
    public String getName(){
        return userData.getName();
    }

    public String getMail(){
        return userData.getEmail();
    }

    public String getPhone(){
        return userData.getPhone();
    }

    public void anthorPatient(View view){
        Intent intent=new Intent(context,AnthorPatientActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,clinicData);
        context.startActivity(intent);
    }
    public void cancelReques(View view){
        Intent intent=new Intent(context,DetailedActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,clinicData);
        context.startActivity(intent);
        ((Activity)context).finishAffinity();
    }

    public void createReservation(View view){
        if(!userData.isStatus()){
            callback.updateUi(ConfigurationFile.Constants.ACTIVE_ACCOUNT_ERROR);
            return;
        }
        request.setCompanyId(clinicData.getId());
        request.setName(userData.getName());
        request.setEmail(userData.getEmail());
        request.setPhone(userData.getPhone());
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

    public void back(View view){
        ((Activity)context).finish();
    }
}
