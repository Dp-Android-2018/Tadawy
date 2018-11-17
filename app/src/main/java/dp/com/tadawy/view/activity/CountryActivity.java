package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCountryBinding;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.adapter.CountriesAdapter;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.view.callback.CountryCallback;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.CountryViewModel;

public class CountryActivity extends BaseActivity implements CountryCallback,BaseInterface {
    private CountryViewModel countryViewModel;
    private ActivityCountryBinding countryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    public void initBinding(){
        countryViewModel=new CountryViewModel(CountryActivity.this,this);
        countryBinding= DataBindingUtil.setContentView(CountryActivity.this, R.layout.activity_country);
        countryBinding.setCountryViewModel(countryViewModel);
        countryBinding.rvCountries.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        countryBinding.actionBar.setViewModel(new ActionBarViewModel(CountryActivity.this));
    }

    @Override
    public void getCountries(Country country) {
        System.out.println("Country Code :"+country.getId());
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA,country);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY,i);
        finish();
    }

    @Override
    public void updateUi(int code) {
        if(code== ConfigurationFile.Constants.COUNTRY_ADAPTER){
            setAdapter();
        }

    }

    public void setAdapter(){
        System.out.println("Size of list is Adapter Setter :"+countryViewModel.responses.size());
        CountriesAdapter adapter=new CountriesAdapter(countryViewModel.responses,this);
        countryBinding.rvCountries.setAdapter(adapter);
    }
}
