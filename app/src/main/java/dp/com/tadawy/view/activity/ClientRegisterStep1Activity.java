package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;


import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityClientRegisterStep1Binding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.ClientRegisterStep1ViewModel;

public class ClientRegisterStep1Activity extends BaseActivity implements BaseInterface {
    private ClientRegisterStep1ViewModel step1ViewModel;
    private ActivityClientRegisterStep1Binding step1Binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        step1ViewModel=new ClientRegisterStep1ViewModel(ClientRegisterStep1Activity.this,this);
        step1Binding= DataBindingUtil.setContentView(ClientRegisterStep1Activity.this,R.layout.activity_client_register_step1);
        step1Binding.setClientRegisterStep1ViewModel(step1ViewModel);
        step1Binding.actionBar.setViewModel(new ActionBarViewModel(ClientRegisterStep1Activity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        step1ViewModel.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.fill_data),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_EMAIL:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.invaled_mail),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.PASSWORD_LENGTH_ERROR:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.password_length),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.PASSWORD_CONFIRMATION_ERROR:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.password_confirmation_error_message),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.EXISET_MAIL_CODE:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.exist_mail_error_message),Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE:{
                Snackbar.make(step1Binding.clRoot,getString(R.string.no_internet_error_message),Snackbar.LENGTH_LONG).show();
                break;
            }
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
