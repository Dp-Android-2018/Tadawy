package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityPendRequestDetailBinding;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.RequestViewModel;

public class PendRequestDetailActivity extends BaseActivity implements BaseInterface {
    RequestViewModel viewModel;
    ActivityPendRequestDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new RequestViewModel(PendRequestDetailActivity.this,this);
        binding=DataBindingUtil.setContentView(PendRequestDetailActivity.this, R.layout.activity_pend_request_detail);
        binding.setViewModel(viewModel);
    }

    @Override
    public void updateUi(int code) {

    }
}
