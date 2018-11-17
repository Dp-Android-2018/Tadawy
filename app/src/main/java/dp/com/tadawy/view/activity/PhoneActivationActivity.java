package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityPhoneActivationBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.PhoneActivationViewModel;

public class PhoneActivationActivity extends BaseActivity implements BaseInterface {
private PhoneActivationViewModel viewModel;
private ActivityPhoneActivationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new PhoneActivationViewModel(PhoneActivationActivity.this,this);
        binding= DataBindingUtil.setContentView(PhoneActivationActivity.this,R.layout.activity_phone_activation);
        binding.setViewModel(viewModel);
        binding.actionBar.setViewModel(new ActionBarViewModel(PhoneActivationActivity.this));
    }

    @Override
    public void updateUi(int code) {
        System.out.println("code on activity : "+code);
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):{
                Snackbar.make(binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.TRY_LATER):{
                Snackbar.make(binding.clRoot,"من فضلك حاول بعد برهة من الزمن",Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
