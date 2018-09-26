package dp.com.tadawy.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityClientRegisterStep2Binding;
import dp.com.tadawy.viewmodel.ClientRegisterStep2ViewModel;

public class ClientRegisterStep2Activity extends AppCompatActivity {
    private ClientRegisterStep2ViewModel step2ViewModel;
    private ActivityClientRegisterStep2Binding step2Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step2ViewModel= ViewModelProviders
                .of(this).get(ClientRegisterStep2ViewModel.class);
        step2Binding= DataBindingUtil.setContentView(ClientRegisterStep2Activity.this, R.layout.activity_client_register_step2);
        step2Binding.setClientRegisterStep2ViewModel(step2ViewModel);
    }
}
