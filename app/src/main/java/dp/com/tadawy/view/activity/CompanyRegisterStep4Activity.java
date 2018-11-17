package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep4Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep4ViewModel;

public class CompanyRegisterStep4Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep4ViewModel step4ViewModel;
    private ActivityCompanyRegisterStep4Binding step4Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step4ViewModel=new CompanyRegisterStep4ViewModel(CompanyRegisterStep4Activity.this,this);
        step4Binding= DataBindingUtil.setContentView(CompanyRegisterStep4Activity.this,R.layout.activity_company_register_step4);
        step4Binding.setViewModel(step4ViewModel);
        step4Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(4));
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(step4Binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
