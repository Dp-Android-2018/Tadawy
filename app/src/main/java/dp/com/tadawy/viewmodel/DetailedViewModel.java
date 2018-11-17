package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableList;
import android.drm.DrmStore;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import dp.com.tadawy.R;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.CommentItem;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.request.CommentRequest;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.CityActivity;
import dp.com.tadawy.view.activity.CountryActivity;
import dp.com.tadawy.view.activity.ReservationActivity;
import dp.com.tadawy.view.activity.SpecializationActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class DetailedViewModel {

    private LoginResponseContent data;
    private Context context;
    private BaseInterface callback;
    String token;
    public ObservableFloat rateBar;
    public ObservableList<CommentItem> commentItems;

    public DetailedViewModel(Context context,BaseInterface callback) {
        this.context=context;
        this.callback=callback;
        data=(LoginResponseContent) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO);
        token="Bearer "+CustomUtils.getInstance().getSaveUserObject(context).getToken();
        commentItems=new ObservableArrayList<>();
        rateBar=new ObservableFloat(data.getAverageRating());
        getCommentsContent();
    }

    public String getLogo(){
        return data.getLogo_url();
    }
    public String getName(){
        return data.getName();
    }
    public String getDistance(){
        return String.valueOf(data.getDistance());
    }

    public String getDescription(){
        return data.getMetaData().getDescription();
    }

    public String getRate(){
        return String.valueOf(data.getAverageRating());
    }

    public String getAddress(){
        return data.getMetaData().getAddress();
    }
    public String getPhone(){
        return data.getPhone();
    }


    public void getCommentsContent(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            ApiClient.getClient().create(EndPoints.class).companyComment(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,data.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companyCommentsResponseResponse -> {
                        if(companyCommentsResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            commentItems.clear();
                            commentItems.addAll(companyCommentsResponseResponse.body().getComments());
                        }
                        System.out.println("comment data of comments : " +commentItems.size());
                        System.out.println("comment code is "+companyCommentsResponseResponse.code());
                        System.out.println("comment token :"+token);

                    }, throwable -> System.out.println("ERROR comments : "+throwable));
        }
    }

    public void addCommentRequest(String comment,float rate){
        CommentRequest commentRequest=new CommentRequest();
        commentRequest.setComment(comment);
        commentRequest.setRating(rate);
        commentRequest.setCompany_id(data.getId());
        System.out.println("Company id : "+data.getId());
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).createComment(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,token,commentRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(defaultResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Code in add comment : "+defaultResponseResponse.code());
                        if(defaultResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_SECOND){
                            callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE);
                            getCommentsContent();
                        }else if(defaultResponseResponse.code()==ConfigurationFile.Constants.ALREADY_ACTIVATED){
                            callback.updateUi(ConfigurationFile.Constants.ALREADY_ACTIVATED);
                        }
                    }, throwable -> System.out.println("ERROR AD COmment "+throwable.getMessage()));
        }else {
            callback.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void booking(View view){
        Intent intent=new Intent(context,ReservationActivity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANYITEMINFO,data);
        //intent.putExtra(ConfigurationFile.IntentConstants.CLINIC_ID,data.getId());
        context.startActivity(intent);
    }


    public void comment(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View filter = inflater.inflate(R.layout.comment_dialog_layout, null);
        EditText comment=filter.findViewById(R.id.et_comment);
        RatingBar rating =filter.findViewById(R.id.rate);
        Button add =filter.findViewById(R.id.add_comment);
        builder.setView(filter);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comment.getText()==null||comment.getText().equals("")||
                        rating.getRating()<=0){
                    callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
                }
                addCommentRequest(comment.getText().toString(),rating.getRating());
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void back(View view){
        ((Activity)context).finish();
    }

}
