package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import dp.com.tadawy.R;
import dp.com.tadawy.application.MyApplication;
import dp.com.tadawy.databinding.ActivityCitiesBinding;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.adapter.CityAdapter;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.view.callback.CityCallback;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.CityViewModel;

public class CityActivity extends BaseActivity implements BaseInterface,CityCallback {
    private CityViewModel cityViewModel;
    private ActivityCitiesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
    }

    public void initVariables(){
        cityViewModel=new CityViewModel(CityActivity.this,this);
        binding= DataBindingUtil.setContentView(CityActivity.this, R.layout.activity_cities);
        binding.setCitiesViewModel(cityViewModel);
        binding.rvCities.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        CityAdapter adapter=new CityAdapter(MyApplication.getCities(),this);
        binding.rvCities.setAdapter(adapter);
        binding.actionBar.setViewModel(new ActionBarViewModel(CityActivity.this));
    }
    @Override
    public void updateUi(int code) {

    }

    @Override
    public void getCities(City city) {
        System.out.println("City Pojo :"+city.getName());
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.CITY_DATA,city);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_CITY,i);
        finish();
    }
}
