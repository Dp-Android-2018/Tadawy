package dp.com.tadawy.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityClientRegisterStep1Binding;
import dp.com.tadawy.viewmodel.ClientRegisterStep1ViewModel;

public class ClientRegisterStep1Activity extends AppCompatActivity {
    private ClientRegisterStep1ViewModel step1ViewModel;
    private ActivityClientRegisterStep1Binding step1Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step1ViewModel= ViewModelProviders
                .of(this).get(ClientRegisterStep1ViewModel.class);
        step1Binding= DataBindingUtil.setContentView(ClientRegisterStep1Activity.this,R.layout.activity_client_register_step1);
        step1Binding.setClientRegisterStep1ViewModel(step1ViewModel);
    }
}
