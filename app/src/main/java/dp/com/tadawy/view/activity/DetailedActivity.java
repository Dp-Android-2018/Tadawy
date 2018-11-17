package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityDetailedBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.DetailedViewModel;

public class DetailedActivity extends BaseActivity implements BaseInterface {
    private DetailedViewModel detailedViewModel;
    private ActivityDetailedBinding detailedBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailedViewModel=new DetailedViewModel(DetailedActivity.this,this);
        detailedBinding= DataBindingUtil.setContentView(DetailedActivity.this,R.layout.activity_detailed);
        detailedBinding.setDetailedViewModel(detailedViewModel);
        detailedBinding.rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.SUCCESS_CODE: {
                Snackbar.make(detailedBinding.svRoot, "تم إضافه التعليق بنجاح", Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE: {
                Snackbar.make(detailedBinding.svRoot,R.string.no_internet_error_message, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.ALREADY_ACTIVATED:{
                Snackbar.make(detailedBinding.svRoot, R.string.more_comments_illagel, Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(detailedBinding.svRoot, R.string.fill_data, Snackbar.LENGTH_LONG).show();
                break;
            }
        }

    }
}
