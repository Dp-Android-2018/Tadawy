package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep1Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep1ViewModel;

public class CompanyRegisterStep1Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep1ViewModel step1ViewModel;
    private ActivityCompanyRegisterStep1Binding step1Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step1ViewModel=new CompanyRegisterStep1ViewModel(this,CompanyRegisterStep1Activity.this);
        step1Binding= DataBindingUtil.setContentView(CompanyRegisterStep1Activity.this,R.layout.activity_company_register_step1);
        step1ViewModel.setClinic(step1Binding.ivClinic);
        step1ViewModel.setCenter(step1Binding.ivCenter);
        step1ViewModel.setHospital(step1Binding.ivHospital);
        step1ViewModel.setLodge(step1Binding.ivLodge);
        step1Binding.setViewModel(step1ViewModel);
        step1Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(1));
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.select_type),Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
