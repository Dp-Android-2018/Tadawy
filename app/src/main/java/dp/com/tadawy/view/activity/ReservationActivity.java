package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityReservationBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ReservationViewModel;

public class ReservationActivity extends BaseActivity  implements BaseInterface {

    private ReservationViewModel viewModel;
    private ActivityReservationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String name=getIntent().getStringExtra(ConfigurationFile.IntentConstants.CLINIC_NAME);
        //int id=getIntent().getIntExtra(ConfigurationFile.IntentConstants.CLINIC_ID,0);
        viewModel=new ReservationViewModel(ReservationActivity.this,this);
        binding=DataBindingUtil.setContentView(ReservationActivity.this,R.layout.activity_reservation);
        binding.setViewModel(viewModel);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(binding.clRoot,R.string.no_internet_error_message,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUCCESS_CODE:{
                Snackbar.make(binding.clRoot,R.string.reservation_success,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.ACTIVE_ACCOUNT_ERROR:{
                Snackbar.make(binding.clRoot,"من فضلك فعًل الحساب اولا",Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
