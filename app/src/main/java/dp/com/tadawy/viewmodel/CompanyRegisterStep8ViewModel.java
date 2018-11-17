package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.view.View;

import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import dp.com.tadawy.R;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.FullDay;
import dp.com.tadawy.pojo.model.WorkDays;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.pojo.response.LoginResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.ConnectTeamWorkActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CompanyRegisterStep8ViewModel {
    private Context context;
    private BaseInterface callBack;
    private CompanyRegisterRequest companyRegisterRequest;
    private WorkDays workDays;
    public WorkDayViewModel sat;
    public WorkDayViewModel sun ;
    public WorkDayViewModel mon ;
    public WorkDayViewModel tus ;
    public WorkDayViewModel wed ;
    public WorkDayViewModel thus;
    public WorkDayViewModel fri ;
    public ObservableInt choice;
    public CompanyRegisterStep8ViewModel(Context context, BaseInterface callBack) {
        this.context = context;
        this.callBack = callBack;
        companyRegisterRequest=(CompanyRegisterRequest)((Activity)context).getIntent().getSerializableExtra(ConfigurationFile.IntentConstants.COMPANY_REQUEST);
        choice=new ObservableInt();
        workDays=new WorkDays();

         sat =new WorkDayViewModel(context);
         sun =new WorkDayViewModel(context);
         mon =new WorkDayViewModel(context);
         tus =new WorkDayViewModel(context);
         wed =new WorkDayViewModel(context);
         thus=new WorkDayViewModel(context);
         fri =new WorkDayViewModel(context);
    }

    public void back(View view){
        ((Activity)context).finish();
    }

    public void selection(View view){
        switch (view.getId()){

            case R.id.tv_sat:{
                choice.set(1);
                break;
            }
            case R.id.tv_sun:{
                choice.set(2);
                break;
            }
            case R.id.tv_mon:{
                choice.set(3);
                break;
            }
            case R.id.tv_tus:{
                choice.set(4);
                break;
            }
            case R.id.tv_wed:{
                choice.set(5);
                break;
            }
            case R.id.tv_thus:{
                choice.set(6);
                break;
            }
            case R.id.tv_fri:{
                choice.set(7);
                break;
            }
        }

    }

    public void register(View view){
        workDays.setSaturday(new FullDay(sat.getMorning(),sat.getNight()));
        workDays.setSunday(new FullDay(sun.getMorning(),sun.getNight()));
        workDays.setMonday(new FullDay(mon.getMorning(),mon.getNight()));
        workDays.setTuesday(new FullDay(tus.getMorning(),tus.getNight()));
        workDays.setWednesday(new FullDay(wed.getMorning(),wed.getNight()));
        workDays.setThursday(new FullDay(thus.getMorning(),thus.getNight()));
        workDays.setFriday(new FullDay(fri.getMorning(),fri.getNight()));
        companyRegisterRequest.setWorkDays(workDays);
        saveData();
        System.out.println("work days "+companyRegisterRequest.getWorkDays().getFriday().getMorning().getFrom());
    }

    public void saveData(){
        if(NetWorkConnection.isConnectingToInternet(context)){
            CustomUtils.getInstance().showProgressDialog(context);
            ApiClient.getClient().create(EndPoints.class).companyRegister(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE,ConfigurationFile.Constants.CONTENT_TYPE,companyRegisterRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("company register code : "+loginResponseResponse.code());
                        if (loginResponseResponse.code()==ConfigurationFile.Constants.SUCCESS_CODE_SECOND){
                            Intent intent=new Intent(context,ConnectTeamWorkActivity.class);
                            context.startActivity(intent);
                            ((Activity)context).finishAffinity();
                        }
                        callBack.updateUi(ConfigurationFile.Constants.INVALED_DATA_CODE);
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR COmpany Register "+throwable.getMessage());

                    });
        }else {
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }
}
