package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityActivationBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ActivationViewModel;

public class ActivationActivity extends BaseActivity implements BaseInterface {
    private ActivationViewModel activationViewModel;
    private ActivityActivationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activationViewModel=new ActivationViewModel(ActivationActivity.this,this);
        binding= DataBindingUtil.setContentView(ActivationActivity.this,R.layout.activity_activation);
        binding.setViewModel(activationViewModel);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE:{
                Snackbar.make(binding.clRoot,"تم إرسال بريد التفعيل بنجاح",Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}