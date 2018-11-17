package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep8Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep8ViewModel;
import dp.com.tadawy.viewmodel.WorkDayViewModel;

public class CompanyRegisterStep8Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep8ViewModel step8ViewModel;
    private ActivityCompanyRegisterStep8Binding step8Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step8ViewModel=new CompanyRegisterStep8ViewModel(CompanyRegisterStep8Activity.this,this);
        step8Binding= DataBindingUtil.setContentView(CompanyRegisterStep8Activity.this,R.layout.activity_company_register_step8);
        step8Binding.setViewModel(step8ViewModel);
        step8Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(8));
        TextView button=findViewById(R.id.tv_sat);
        step8ViewModel.selection(button);
        step8Binding.vSat.setViewModel(step8ViewModel.sat);
        step8Binding.vSun.setViewModel(step8ViewModel.sun);
        step8Binding.vMon.setViewModel(step8ViewModel.mon);
        step8Binding.vTus.setViewModel(step8ViewModel.tus);
        step8Binding.vWed.setViewModel(step8ViewModel.wed);
        step8Binding.vThus.setViewModel(step8ViewModel.thus);
        step8Binding.vFri.setViewModel(step8ViewModel.fri);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.INVALED_DATA_CODE:{
                Snackbar.make(step8Binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(step8Binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
