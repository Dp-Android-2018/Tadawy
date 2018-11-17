package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep6Binding;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep6ViewModel;

public class CompanyRegisterStep6Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep6ViewModel step6ViewModel;
    private ActivityCompanyRegisterStep6Binding step6Binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step6ViewModel=new CompanyRegisterStep6ViewModel(CompanyRegisterStep6Activity.this,this);
        step6Binding= DataBindingUtil.setContentView(CompanyRegisterStep6Activity.this,R.layout.activity_company_register_step6);
        step6Binding.setViewModel(step6ViewModel);
        step6Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(6));
    }

    @Override
    public void updateUi(int code) {

    }
}
