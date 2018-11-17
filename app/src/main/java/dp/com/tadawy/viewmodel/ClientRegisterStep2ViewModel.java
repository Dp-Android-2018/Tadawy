package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.request.CheckPhoneRequest;
import dp.com.tadawy.pojo.request.ClientRegisterRequest;
import dp.com.tadawy.pojo.response.LoginResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.utils.SharedPrefrenceUtils;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.ActivationActivity;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ClientRegisterStep2ViewModel {
    Context context;
    BaseInterface callBack;
    public ObservableField<String>phone;
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    ClientRegisterRequest registerRequest;
    CheckPhoneRequest phoneRequest;
    boolean cityFlag;
    private SharedPrefrenceUtils pref;
    Country country;
    City city;

    public ClientRegisterStep2ViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        registerRequest=(ClientRegisterRequest) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.CLIENT_REQUEST);
        System.out.println("Request1_2 is : "+registerRequest.getLogo());
        System.out.println("Request1_2 is : "+registerRequest.getName());
        System.out.println("Request1_2 is : "+registerRequest.getEmail());
        System.out.println("Request1_2 is : "+registerRequest.getPassword());
        System.out.println("Request1_2 is : "+registerRequest.getPasswordConfirmation());

        initVariables();
    }
    public void initVariables(){
        phone=new ObservableField<>();
        countryName =new ObservableField<>();
        cityName =new ObservableField<>();
        cityFlag=false;
    }
    public void checkData(View view) {
        if (ValidationUtils.isEmpty(phone.get()) ||
                ValidationUtils.isEmpty(countryName.get()) ||
                ValidationUtils.isEmpty(cityName.get())) {
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else{
            phoneRequest=new CheckPhoneRequest(phone.get());
            checkEsistPhone();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY) {
                cityFlag=true;
                country = (Country) data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
                setCountryName();
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_CITY) {
                city = (City) data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
                setCityName();
            }
        }
    }



    public void getCountries(View view){
        callBack.updateUi(ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT);
    }

    public void getCities(View view){
        if(cityFlag) {
            callBack.updateUi(ConfigurationFile.Constants.MOVE_TO_CITY_ACT);
        }
        else{
            callBack.updateUi(ConfigurationFile.Constants.SELECT_COUNTRY);
        }
    }

    public void setCountryName(){
        countryName.set(country.getName());
    }
    public void setCityName(){
        cityName.set(city.getName());
    }


    public void checkEsistPhone(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog((Activity)context);
            ApiClient.getClient().create(EndPoints.class).checkPhone(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,phoneRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(integerResponse -> {
                        System.out.println("Check phone code : "+integerResponse.code());
                        CustomUtils.getInstance().cancelDialog();
                        if(integerResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE){
                            if(integerResponse.body()==0){
                                registerRequest.setPhone(phone.get());
                                registerRequest.setCity_id(city.getId());
                                System.out.println("Request is : "+registerRequest.getLogo());
                                System.out.println("Request is : "+registerRequest.getEmail());
                                System.out.println("Request is : "+registerRequest.getName());
                                System.out.println("Request is : "+registerRequest.getPassword());
                                System.out.println("Request is : "+registerRequest.getPasswordConfirmation());
                                System.out.println("Request is : "+registerRequest.getPhone());
                                System.out.println("Request is : "+registerRequest.getCity_id());
                                register();
                            }else{

                                callBack.updateUi(ConfigurationFile.Constants.EXISET_PHONE_CODE);
                            }
                        }

                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Exiset phone Ex:"+throwable.getMessage());

                    });
        }else{
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void register(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).clientRegister(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,registerRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Response<LoginResponse>>() {
                        @Override
                        public void accept(Response<LoginResponse> loginResponseResponse) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("ClientRegister code :"+loginResponseResponse.code());
                            System.out.println("Client register message :"+loginResponseResponse.body());
                            switch (loginResponseResponse.code()){
                                case ConfigurationFile.Constants.SUCCESS_CODE_SECOND:{
                                    saveDataToPrefs(loginResponseResponse.body().getLoginResponseContent());
                                    Intent intent=new Intent(context,ActivationActivity.class);
                                    context.startActivity(intent);
                                    ((Activity)context).finishAffinity();
                                    break;
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            CustomUtils.getInstance().cancelDialog();
                            System.out.println("ERROR CLIENT REGISTER :"+throwable.getMessage());

                        }
                    });
        }else {
            CustomUtils.getInstance().cancelDialog();
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void saveDataToPrefs(LoginResponseContent data){
        pref=new SharedPrefrenceUtils(context);
        pref.saveObjectToSharedPreferences(ConfigurationFile.SharedPrefConstants.SHARED_PREF_NAME,data);
    }
}
