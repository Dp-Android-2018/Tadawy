package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.view.View;

import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.activity.CompanyRegisterStep6Activity;
import dp.com.tadawy.view.callback.BaseInterface;

public class CompanyRegisterStep5ViewModel {
    Context context;
    BaseInterface callBask;
    CompanyRegisterRequest companyRegisterRequest;
    public ObservableField<String>companyName;
    public ObservableList<String> items;

    public CompanyRegisterStep5ViewModel(Context context, BaseInterface callBask) {
        this.context = context;
        this.callBask = callBask;
        companyRegisterRequest=(CompanyRegisterRequest) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
        companyName=new ObservableField<>();
        items=new ObservableArrayList<>();
    }

    public void next(View view){
        if(items.size()<=0){
            callBask.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else{
            companyRegisterRequest.setInsuranceCompanies(items);
        Intent intent=new Intent(context,CompanyRegisterStep6Activity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
        context.startActivity(intent);
        }
    }

    public void back(View view){
        ((Activity)context).finish();
    }

    public void add(View view){
        items.add(companyName.get());
        companyName.set("");
    }


}
