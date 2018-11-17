package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dp.com.tadawy.R;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.CityActivity;
import dp.com.tadawy.view.activity.CountryActivity;
import dp.com.tadawy.view.activity.RequestActivity;
import dp.com.tadawy.view.activity.SpecializationActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RequestViewModel {
    Context context;
    BaseInterface callback;
    private ReservationContent reservationContent;

    public RequestViewModel(Context context,BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        reservationContent=(ReservationContent) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.REQUEST_ITEM_DATA);
    }

    public void back(View view){
        ((Activity)context).finish();
    }

    public String getName(){
        return reservationContent.getName();
    }

    public String getMail(){
        return reservationContent.getEmail();
    }

    public String getPhone(){
        return reservationContent.getPhone();
    }

    public String getComment(){
        return reservationContent.getComment();
    }

    public void cancelRequest(View view){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).deleteReservation(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken(),reservationContent.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("delete reservation code : "+defaultResponseResponse.code());
                        if(defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            Intent intent=new Intent(context,RequestActivity.class);
                            context.startActivity(intent);
                            ((Activity)context).finishAffinity();
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR delete reservation : "+throwable.getMessage());

                    });

        }else{
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void acceptRequest(View view1){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View filter = inflater.inflate(R.layout.approve_request_dialog, null);
        Button btAccept=filter.findViewById(R.id.accept);
        Button btCancel=filter.findViewById(R.id.exit);
        EditText comment=filter.findViewById(R.id.comment);
        builder.setView(filter);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        btAccept.setOnClickListener(v -> {
            callRequest(comment.getText().toString());
            dialog.dismiss();

        });
        btCancel.setOnClickListener(v -> {
            dialog.dismiss();
                });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void callRequest(String comment){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).approveRequest(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,
                    "Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken(),
                    reservationContent.getId(),comment)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Approve request code : "+defaultResponseResponse.code());

                        if(defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            Intent intent=new Intent(context,RequestActivity.class);
                            context.startActivity(intent);
                            ((Activity)context).finishAffinity();
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR approve request : "+throwable.getMessage());

                    });

        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }

    }
}
