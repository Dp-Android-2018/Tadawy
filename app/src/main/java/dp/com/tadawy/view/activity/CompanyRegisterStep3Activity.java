package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep3Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep3ViewModel;

public class CompanyRegisterStep3Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep3ViewModel step3ViewModel;
    private ActivityCompanyRegisterStep3Binding step3Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step3ViewModel=new CompanyRegisterStep3ViewModel(CompanyRegisterStep3Activity.this,this);
        step3Binding=DataBindingUtil.setContentView(CompanyRegisterStep3Activity.this,R.layout.activity_company_register_step3);
        step3Binding.setViewModel(step3ViewModel);
        step3Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(3));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        step3ViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:
            {
                Snackbar.make(step3Binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:
            {
                Snackbar.make(step3Binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_COUNTRY_ACT:
            {
                Intent i=new Intent(this, CountryActivity.class);
                startActivityForResult(i,ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY);
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_CITY_ACT:
            {
                Intent i = new Intent(this,CityActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CITY);
                break;
            }case ConfigurationFile.Constants.MOVE_TO_MAP_ACT:
            {
                Intent i = new Intent(this,MapsActivity.class);
                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_MAP);
                break;
            }case ConfigurationFile.Constants.SELECT_COUNTRY:
            {
                Snackbar.make(step3Binding.clRoot,R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.MOVE_TO_SPECIALIZATION_ACT:
            {
//                Intent i = new Intent(this,SpecializationActivity.class);
//                i.putExtra(ConfigurationFile.IntentConstants.TYPE,step3ViewModel.)
//                startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION);
                break;
            }
        }

    }
}
