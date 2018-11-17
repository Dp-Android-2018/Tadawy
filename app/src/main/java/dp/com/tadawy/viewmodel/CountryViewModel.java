package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CountryViewModel {
    private Context context;
    public ObservableList<Country> responses;
    List<City> cities;
    private BaseInterface callback;
    public CountryViewModel(Context context, BaseInterface callback){
        this.context = context;
        this.callback=callback;
        responses=new ObservableArrayList<>();
        cities=new ArrayList<>();
        getCountries();
    }


    public void getCountries(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).getCountries(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(countryResponseResponse -> {
                        System.out.println("Code of countries is  :"+countryResponseResponse.code());
                        if (countryResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            CustomUtils.getInstance().cancelDialog();
                            responses.addAll(countryResponseResponse.body().getCountries());
                            System.out.println("Size of list is : "+responses.size());
                            callback.updateUi(ConfigurationFile.Constants.COUNTRY_ADAPTER);
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR here is  : "+throwable.getMessage());

                    });

        }else{

        }
    }


}
