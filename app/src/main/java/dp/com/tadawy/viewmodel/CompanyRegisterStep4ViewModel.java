package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.ValidationUtils;
import dp.com.tadawy.view.activity.CompanyRegisterStep3Activity;
import dp.com.tadawy.view.activity.CompanyRegisterStep5Activity;
import dp.com.tadawy.view.callback.BaseInterface;

public class CompanyRegisterStep4ViewModel {

    Context context;
    BaseInterface callBack;
    CompanyRegisterRequest companyRegisterRequest;
    public ObservableField<String> mobile;
    public ObservableField<String> phone;
    public ObservableField<String>website;
    public ObservableField<String>description;

    public CompanyRegisterStep4ViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        companyRegisterRequest=(CompanyRegisterRequest) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
        mobile=new ObservableField<>();
        phone=new ObservableField<>();
        website=new ObservableField<>();
        description=new ObservableField<>();
    }

    public void next(View view){
        checkData();
    }

    public void checkData(){
        if(ValidationUtils.isEmpty(mobile.get())||
           ValidationUtils.isEmpty(phone.get())||
           ValidationUtils.isEmpty(website.get())||
           ValidationUtils.isEmpty(description.get())){
            callBack.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
        }else {
            companyRegisterRequest.setPhone(phone.get());
            companyRegisterRequest.setWebsite(website.get());
            companyRegisterRequest.setDescription(description.get());
            Intent intent=new Intent(context,CompanyRegisterStep5Activity.class);
            intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
            context.startActivity(intent);
        }

    }

    public void back(View view){
        ((Activity)context).finish();
    }
}
