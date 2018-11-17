package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep5Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep4ViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep5ViewModel;

public class CompanyRegisterStep5Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep5ViewModel step5ViewModel;
    private ActivityCompanyRegisterStep5Binding step5Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step5ViewModel=new CompanyRegisterStep5ViewModel(CompanyRegisterStep5Activity.this,this);
        step5Binding= DataBindingUtil.setContentView(CompanyRegisterStep5Activity.this,R.layout.activity_company_register_step5);
        step5Binding.setViewModel(step5ViewModel);
        step5Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(5));
        step5Binding.rvCompanies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }
    @Override
    public void updateUi(int code) {

        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(step5Binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
