package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import java.util.Observable;

import dp.com.tadawy.pojo.model.SocialNetwork;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.activity.CompanyRegisterStep3Activity;
import dp.com.tadawy.view.activity.CompanyRegisterStep7Activity;
import dp.com.tadawy.view.callback.BaseInterface;

public class CompanyRegisterStep6ViewModel {
    Context context;
    BaseInterface callback;
    CompanyRegisterRequest companyRegisterRequest;
    public ObservableField<String> facebook;
    public ObservableField<String> twitter;
    public ObservableField<String> instgram;
    SocialNetwork socialNetwork;

    public CompanyRegisterStep6ViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback = callback;
        socialNetwork=new SocialNetwork();
        companyRegisterRequest=(CompanyRegisterRequest) ((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
        facebook=new ObservableField<>();
        twitter=new ObservableField<>();
        instgram=new ObservableField<>();
    }

    public void next(View view){
        if(facebook.get()!=null||facebook.get()!=""){
           socialNetwork.setFacebook(facebook.get());
        }
        if(twitter.get()!=null||twitter.get()!=""){
            socialNetwork.setTwitter(twitter.get());
        }
        if(instgram.get()!=null||instgram.get()!=""){
            socialNetwork.setInstagram(instgram.get());
        }
        companyRegisterRequest.setSocialNetwork(socialNetwork);
        Intent intent=new Intent(context,CompanyRegisterStep7Activity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
        context.startActivity(intent);
    }

    public void back(View view){
        ((Activity)context).finish();
    }

}
