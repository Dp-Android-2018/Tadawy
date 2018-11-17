package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import dp.com.tadawy.R;
import dp.com.tadawy.application.MyApplication;
import dp.com.tadawy.databinding.ActivitySpecializationBinding;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.adapter.SpecializationAdapter;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.view.callback.SpecializationCallback;
import dp.com.tadawy.viewmodel.ActionBarViewModel;
import dp.com.tadawy.viewmodel.SpecializationViewModel;

public class SpecializationActivity extends BaseActivity implements SpecializationCallback ,BaseInterface {
    private SpecializationViewModel specializationViewModel;
    private ActivitySpecializationBinding specializationBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
    }

    @Override
    public void getSpecialization(Specialization specialization) {
        Intent i=getIntent();
        i.putExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA,specialization);
        setResult(ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION,i);
        finish();
    }

    public void initBinding(){
        specializationViewModel=new SpecializationViewModel(SpecializationActivity.this,this);
        specializationBinding= DataBindingUtil.setContentView(SpecializationActivity.this, R.layout.activity_specialization);
        specializationBinding.setSpecializationViewModel(specializationViewModel);
        specializationBinding.rvSpecialization.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        specializationBinding.actionBar.setViewModel(new ActionBarViewModel(SpecializationActivity.this));

    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.SPECIALIZATION_ADAPTER:{
                SpecializationAdapter adapter=new SpecializationAdapter(specializationViewModel.specializations,this);
                specializationBinding.rvSpecialization.setAdapter(adapter);
                break;
            }
        }
    }
}
