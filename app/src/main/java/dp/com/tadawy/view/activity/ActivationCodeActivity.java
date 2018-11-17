package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityActivationCodeBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.ActivationCodeViewModel;

public class ActivationCodeActivity extends BaseActivity implements BaseInterface {
    private ActivationCodeViewModel viewModel;
    private ActivityActivationCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new ActivationCodeViewModel(this,this);
        binding= DataBindingUtil.setContentView(ActivationCodeActivity.this,R.layout.activity_activation_code);
        binding.setViewModel(viewModel);
        binding.actionBar.setViewModel(new ActionBarViewModel(ActivationCodeActivity.this));
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case (ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE):{
                Snackbar.make(binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case (ConfigurationFile.Constants.FILL_ALL_DATA_ERROR):{
                Snackbar.make(binding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }

        }

    }
}
