package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.pojo.response.ReservationResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class NotificationViewModel {
    private LoginResponseContent data;
    public ObservableList<ReservationContent> reservations;
    public ObservableField<String>status;
    BaseInterface callback;
    public boolean visibility=false;
    Context context;
    String token="Bearer ";

    public NotificationViewModel(Context context,BaseInterface callback,String status) {
        this.context = context;
        this.callback=callback;
        reservations=new ObservableArrayList<>();
        data=(LoginResponseContent)CustomUtils.getInstance().getSaveUserObject(context);
        token+=data.getToken();
        this.status=new ObservableField<>(status);
        getReservation(status);
    }

    public void getReservation(String status){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getReservation(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,status)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(reservationResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("RESERVATION CODE : "+reservationResponseResponse.code());
                        if(reservationResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            reservations.addAll(reservationResponseResponse.body().getReservations());
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR IS : "+throwable.getMessage());

                    });
            if(reservations.size()<=0){
                visibility=true;
            }
        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
