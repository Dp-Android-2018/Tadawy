package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityChangePasswordBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ChangePasswordViewModel;

public class ChangePasswordActivity extends BaseActivity implements BaseInterface {
    private ChangePasswordViewModel viewModel;
    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ChangePasswordViewModel(ChangePasswordActivity.this,this);
        binding=DataBindingUtil.setContentView(ChangePasswordActivity.this,R.layout.activity_change_password);
        binding.setViewModel(viewModel);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.PASSWORD_LENGTH_ERROR:{
                Snackbar.make(binding.clRoot,R.string.password_length,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.PASSWORD_CONFIRMATION_ERROR:{
                Snackbar.make(binding.clRoot,R.string.password_confirmation_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD:{
                Snackbar.make(binding.clRoot,R.string.invaled_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE:{
                Snackbar.make(binding.clRoot,getString(R.string.password_changed_successfully),Snackbar.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                },3000);
                break;
            }
        }

    }
}
