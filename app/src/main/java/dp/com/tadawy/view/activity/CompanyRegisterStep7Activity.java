package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityCompanyRegisterStep7Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.CompanyRegisterCounterViewModel;
import dp.com.tadawy.viewmodel.CompanyRegisterStep7ViewModel;

public class CompanyRegisterStep7Activity extends BaseActivity implements BaseInterface {

    private CompanyRegisterStep7ViewModel step7ViewModel;
    private ActivityCompanyRegisterStep7Binding step7Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        step7ViewModel=new CompanyRegisterStep7ViewModel(CompanyRegisterStep7Activity.this,this);
        step7Binding= DataBindingUtil.setContentView(CompanyRegisterStep7Activity.this,R.layout.activity_company_register_step7);
        step7Binding.setViewModel(step7ViewModel);
        step7Binding.counter.setViewModel(new CompanyRegisterCounterViewModel(7));
        step7Binding.rvImages.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        step7ViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.GETIMAGE:
            {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
                //pickPhoto.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(pickPhoto , ConfigurationFile.Constants.PICK_IMAGE_REQUEST);
                break;
            }
        }

    }
}
