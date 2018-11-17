package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityApproveRequestDetailBinding;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.RequestViewModel;

public class DetailItemApproveRequestActivity extends BaseActivity implements BaseInterface {
    private RequestViewModel viewModel;
    private ActivityApproveRequestDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel=new RequestViewModel(DetailItemApproveRequestActivity.this,this);
        binding=DataBindingUtil.setContentView(DetailItemApproveRequestActivity.this,R.layout.activity_approve_request_detail);
        binding.setViewModel(viewModel);
    }

    @Override
    public void updateUi(int code) {

    }
}
