package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityAnthorPatientReservationBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.AnthorPatientViewModel;

public class AnthorPatientActivity extends BaseActivity implements BaseInterface {

    private AnthorPatientViewModel viewModel;
    private ActivityAnthorPatientReservationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // String name=getIntent().getStringExtra(ConfigurationFile.IntentConstants.CLINIC_NAME);
        //int id=getIntent().getIntExtra(ConfigurationFile.IntentConstants.CLINIC_ID,0);
        viewModel=new AnthorPatientViewModel(AnthorPatientActivity.this,this);
        binding= DataBindingUtil.setContentView(AnthorPatientActivity.this,R.layout.activity_anthor_patient_reservation);
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
            case ConfigurationFile.Constants.INVALED_EMAIL:{
                Snackbar.make(binding.clRoot,R.string.invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE:{
                Snackbar.make(binding.clRoot,R.string.reservation_success,Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
