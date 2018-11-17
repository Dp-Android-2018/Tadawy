package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.airbnb.lottie.animation.content.ContentGroup;

import java.util.ArrayList;
import java.util.List;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.pojo.response.SpecializationResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.adapter.ListCompaniesAdapter;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SpecializationViewModel {
    Context context;
    String type;
    public List<Specialization> specializations;
    BaseInterface callback;

    public SpecializationViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback=callback;
        specializations=new ArrayList<>();
        type=((Activity)context).getIntent().getStringExtra(ConfigurationFile.IntentConstants.TYPE);
        getSpecialization();
    }

    public void getSpecialization(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getSpecialization(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,type)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<SpecializationResponse>>() {
                        @Override
                        public void accept(Response<SpecializationResponse> specializationResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("SpecializationResponse code : "+specializationResponseResponse.code());
                            if (specializationResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                                specializations.addAll(specializationResponseResponse.body().getSpecializations());
                                System.out.println("Specialization size is : "+specializations.size());
                                callback.updateUi(ConfigurationFile.Constants.SPECIALIZATION_ADAPTER);

                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            Log.w("Error Spec",throwable.getMessage());
                            System.out.println("ERROR specialization : "+throwable.getMessage());

                        }
                    });
        }

    }
}
