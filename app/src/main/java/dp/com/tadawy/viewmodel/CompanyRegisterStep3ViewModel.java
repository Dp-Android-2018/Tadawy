package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.location.Location;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.pojo.request.CheckPhoneRequest;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.CompanyRegisterStep3Activity;
import dp.com.tadawy.view.activity.CompanyRegisterStep4Activity;
import dp.com.tadawy.view.activity.MapsActivity;
import dp.com.tadawy.view.activity.SpecializationActivity;
import dp.com.tadawy.view.callback.BaseInterface;

public class CompanyRegisterStep3ViewModel {
    Context context;
    BaseInterface callBack;
    CompanyRegisterRequest companyRegisterRequest;//=new CompanyRegisterRequest();
    public ObservableField<String> countryName;
    public ObservableField<String> cityName;
    public ObservableField<String> address;
    public ObservableField<String> specializationName;
    boolean cityFlag;
    Country country;
    City city;
    double lat;
    double lang;
    Specialization specialization;
    public CompanyRegisterStep3ViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        initVariables();
        System.out.println("Type is "+companyRegisterRequest.getType());
    }
    public void initVariables(){
        countryName =new ObservableField<>();
        cityName =new ObservableField<>();
        address=new ObservableField<>();
        cityFlag=false;
        specializationName=new ObservableField<>();
        companyRegisterRequest =(CompanyRegisterRequest)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
    }
    public void next(View view){
        checkData();
    }
    public void back(View view){
        ((Activity)context).finish();
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
            }else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_MAP){
                address.set((String)data.getStringExtra(ConfigurationFile.IntentConstants.ADDRESS_REQUEST));
                lang=(Double)data.getDoubleExtra(ConfigurationFile.IntentConstants.LONGITUDE_REQUEST,0);
                lat=(Double)data.getDoubleExtra(ConfigurationFile.IntentConstants.LATITUDE_REQUEST,0);
                System.out.println("Long in view Model:"+lang+"Lat :"+lat);
               // companyRegisterRequest.setLat((Double)data.getDoubleExtra(ConfigurationFile.IntentConstants.LATITUDE_REQUEST,0));
                //companyRegisterRequest.setLng((Double)data.getDoubleExtra(ConfigurationFile.IntentConstants.LONGITUDE_REQUEST,0));
            }else if (requestCode==ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION) {
                specialization=(Specialization)data.getSerializableExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA);
                setSpecializationName();
            }
        }
    }

    public void checkData() {
        if (ValidationUtils.isEmpty(countryName.get()) ||
                ValidationUtils.isEmpty(cityName.get())||
                ValidationUtils.isEmpty(address.get())||
                ValidationUtils.isEmpty(specializationName.get())
                ) {
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else{
            System.out.println("Address is : "+address.get());
            companyRegisterRequest.setAddress(address.get());
            companyRegisterRequest.setLat(lat);
            companyRegisterRequest.setLng(lang);
            companyRegisterRequest.setCityId(city.getId());
            companyRegisterRequest.setSpecializationId(specialization.getId());
            Intent intent=new Intent(context,CompanyRegisterStep4Activity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
            context.startActivity(intent);
        }
    }
    public void getLocation(View view){
        callBack.updateUi(ConfigurationFile.Constants.MOVE_TO_MAP_ACT);
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

    public void getSpecialization(View view){
        Intent i = new Intent(context,SpecializationActivity.class);
        i.putExtra(ConfigurationFile.IntentConstants.TYPE,companyRegisterRequest.getType());
        ((Activity)context).startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION);
    }

    public void setSpecializationName(){
        specializationName.set(specialization.getName());
    }






}
