package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import dp.com.tadawy.R;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.activity.ClientRegisterStep1Activity;
import dp.com.tadawy.view.activity.CompanyRegisterStep2Activity;
import dp.com.tadawy.view.callback.BaseInterface;

public class CompanyRegisterStep1ViewModel {
    ImageView clinic;
    ImageView center;
    ImageView lodge;
    ImageView hospital;
    int selection;
    BaseInterface callback;
    Context context;
    private CompanyRegisterRequest companyRegisterRequest;

    public CompanyRegisterStep1ViewModel(BaseInterface callback,Context context) {
        this.callback = callback;
        this.context=context;
        selection=0;
    }

    public void setClinic(ImageView clinic) {
        this.clinic = clinic;
        companyRegisterRequest=new CompanyRegisterRequest();
    }

    public void setCenter(ImageView center) {
        this.center = center;
    }

    public void setLodge(ImageView lodge) {
        this.lodge = lodge;
    }

    public void setHospital(ImageView hospital) {
        this.hospital = hospital;
    }

    public void select(View view){
        switch (view.getId()){
            case R.id.iv_clinic:{
                clinic.setImageResource(R.drawable.clincks_image_clicked);
                center.setImageResource(R.drawable.center_image);
                lodge.setImageResource(R.drawable.motag3_image);
                hospital.setImageResource(R.drawable.hospital_image);
                selection=1;
                break;

            }
            case R.id.iv_center:{
                clinic.setImageResource(R.drawable.clincks_image);
                center.setImageResource(R.drawable.center_image_clicked);
                lodge.setImageResource(R.drawable.motag3_image);
                hospital.setImageResource(R.drawable.hospital_image);
                selection=4;
                break;

            }
            case R.id.iv_lodge:{
                clinic.setImageResource(R.drawable.clincks_image);
                center.setImageResource(R.drawable.center_image);
                lodge.setImageResource(R.drawable.motag3_image_clicked);
                hospital.setImageResource(R.drawable.hospital_image);
                selection=2;
                break;

            }
            case R.id.iv_hospital:{
                clinic.setImageResource(R.drawable.clincks_image);
                center.setImageResource(R.drawable.center_image);
                lodge.setImageResource(R.drawable.motag3_image);
                hospital.setImageResource(R.drawable.hospital_image_clicked);
                selection=3;
                break;
            }
        }
    }

    public void next(View view){
        switch (selection)
        {
            case 0:{
                callback.updateUi(ConfigurationFile.Constants.FILL_ALL_DATA_ERROR);
                return;
            }
            case 1:{
                companyRegisterRequest.setType("CLINIC");
                break;
            }
            case 2:{
                companyRegisterRequest.setType("SPA");
                break;
            }
            case 3:{
                companyRegisterRequest.setType("HOSPITAL");
                break;
            }
            case 4:{
                companyRegisterRequest.setType("FIRM");
                break;
            }
        }

        Intent intent=new Intent(context,CompanyRegisterStep2Activity.class);
        intent.putExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST,companyRegisterRequest);
        context.startActivity(intent);
    }

    public void back(View view){
        ((Activity)context).finish();
    }

}
