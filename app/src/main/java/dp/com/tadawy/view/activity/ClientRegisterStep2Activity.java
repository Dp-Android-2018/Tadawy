package dp.com.tadawy.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityClientRegisterStep2Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.ClientRegisterStep2ViewModel;

public class ClientRegisterStep2Activity extends BaseActivity implements BaseInterface {
    private ClientRegisterStep2ViewModel step2ViewModel;
    private ActivityClientRegisterStep2Binding step2Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step2ViewModel=new ClientRegisterStep2ViewModel(ClientRegisterStep2Activity.this,this);
        step2Binding= DataBindingUtil.setContentView(ClientRegisterStep2Activity.this, R.layout.activity_client_register_step2);
        step2Binding.setClientRegisterStep2ViewModel(step2ViewModel);
        step2Binding.actionBar.setViewModel(new ActionBarViewModel(ClientRegisterStep2Activity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        step2ViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:
                {
                Snackbar.make(step2Binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:
            {
                Snackbar.make(step2Binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.EXISET_PHONE_CODE:
            {
                Snackbar.make(step2Binding.clRoot, R.string.exist_phone_error_message,Snackbar.LENGTH_LONG).show();
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
            }case ConfigurationFile.Constants.SELECT_COUNTRY:
            {
                Snackbar.make(step2Binding.clRoot,R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
