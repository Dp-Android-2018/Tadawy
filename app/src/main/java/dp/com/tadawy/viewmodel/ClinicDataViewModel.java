package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import dp.com.tadawy.R;
import dp.com.tadawy.network.ApiClient;
import dp.com.tadawy.network.EndPoints;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.model.Specialization;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.utils.NetWorkConnection;
import dp.com.tadawy.view.activity.CityActivity;
import dp.com.tadawy.view.activity.CountryActivity;
import dp.com.tadawy.view.activity.SpecializationActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClinicDataViewModel {
    public ObservableInt gridVisibility;
    public ObservableInt linearVisibility;
    public ObservableInt linearOrGrid;
    private Context context;
    private String savedType;
    private BaseInterface callBack;
    public ObservableInt dataImageVisability;
    public ObservableList<LoginResponseContent> searchResponse;
    private String type;
    public ObservableField<String> text;
    public ObservableInt visibality;
    private String next=null;
    private String pageId="0";
    private boolean loading=false;
    boolean cityFlag=false;
    Country country;
    City city;
    Specialization specialization;
    public ObservableField<String>title;
    Button btCountry;
    Button btCity;
    Button btSpecialization;
    public ClinicDataViewModel(Context context,BaseInterface callBack,int x) {
        this.context=context;
        this.callBack=callBack;
        initVariables();
        switch (x){
            case 0:
                type=ConfigurationFile.Constants.CLINIC;
                title.set("جميع العيادات");
                break;
            case 1:
                type=ConfigurationFile.Constants.SPA;
                title.set("جميع المنتجعات");
                break;
            case 2:
                type=ConfigurationFile.Constants.HOSPITAL;
                title.set("جميع المستشفيات");
                break;
            case 3:
                type=ConfigurationFile.Constants.FIRM;
                title.set("جميع المراكز الصحيه");
                break;
        }
        search(pageId,null,null,null,type);
    }

    public void initVariables(){
        gridVisibility=new ObservableInt(View.GONE);
        linearVisibility=new ObservableInt(View.VISIBLE);
        linearOrGrid=new ObservableInt(0);
        searchResponse=new ObservableArrayList<>();
        text=new ObservableField<>();
        visibality=new ObservableInt(View.GONE);
        title=new ObservableField<>();
        dataImageVisability=new ObservableInt(View.GONE);
    }

    public void selectLinearView(View view){
        linearVisibility.set(View.VISIBLE);
        gridVisibility.set(View.GONE);
        linearOrGrid.set(0);
    }

    public void selectGridView(View view){
        linearVisibility.set(View.GONE);
        gridVisibility.set(View.VISIBLE);
        linearOrGrid.set(1);
    }

    public RecyclerView.OnScrollListener onScrollListener(){
        return new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()==searchResponse.size()-1) {
                    if (next != null && loading == false) {
                        // loading = true;
                        System.out.println("Load More Data Success ");
                        search(pageId,null,null,null,type);
                    }
                }
            }
        };
    }

    public void search(String _pageID,String countryId,String cityId,String specializationId,String type) {
        System.out.println("***page id is : "+_pageID);
        System.out.println("***type : "+type);
        System.out.println("***country id is : "+countryId);
        System.out.println("***City id is : "+cityId);
        System.out.println("***specialization id is : "+specializationId);
        savedType=type;
        if (NetWorkConnection.isConnectingToInternet(context)) {
            CustomUtils.getInstance().showProgressDialog(context);
            loading=true;
            System.out.println("After Loading : "+loading);
            ApiClient.getClient().create(EndPoints.class).companySearch(ConfigurationFile.Constants.API_KEY,
                    ConfigurationFile.Constants.CONTENT_TYPE, ConfigurationFile.Constants.CONTENT_TYPE,
                    countryId,cityId,specializationId,type,_pageID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(companiesSearchResponseResponse -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("Country Is :"+countryId);
                        System.out.println("Code is : "+companiesSearchResponseResponse.code());
                        System.out.println("ids on company search view Model :"+countryId+"  id2 : "+cityId+"  id3 : ");
                        if (companiesSearchResponseResponse.code() == ConfigurationFile.Constants.SUCCESS_CODE) {
                            if(companiesSearchResponseResponse.body().getSearchResponses().size()==0){
                                callBack.updateUi(999);
                            }
                            //callback.updateUi(ConfigurationFile.Constants.SUCCESS_CODE_second);
                            if(_pageID=="0"){
                                searchResponse.clear();
                            }
                            next=companiesSearchResponseResponse.body().getLinks().getNext();
                            System.out.println("next is : "+next);
                            if(next!=null){
                                pageId=next.substring(next.length()-1);
                            }
                            searchResponse.addAll(companiesSearchResponseResponse.body().getSearchResponses());
                            System.out.println("Size is : "+searchResponse.size());
                            loading=false;
                            if(searchResponse.size()<=0){
                                dataImageVisability.set(View.VISIBLE);
                                gridVisibility.set(View.GONE);
                                linearVisibility.set(View.GONE);
                            }
                        }
                    }, throwable -> {
                        CustomUtils.getInstance().cancelDialog();
                        System.out.println("ERROR Search : "+throwable.getMessage());
                        text.set("حدث خطأ فى المصادفة تأكد من إتصال الانترنت");
                        visibality.set(View.VISIBLE);
                    });

        } else {
            callBack.updateUi(ConfigurationFile.Constants.NO_INTERNET_CONNECTION_CODE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data != null) {
            if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY) {
                cityFlag = true;
                country = (Country) data.getSerializableExtra(ConfigurationFile.IntentConstants.COUNTRY_DATA);
                btCountry.setText(country.getName());
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_CITY) {
                city = (City) data.getSerializableExtra(ConfigurationFile.IntentConstants.CITY_DATA);
                btCity.setText(city.getName());
            } else if (requestCode == ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION) {
                specialization = (Specialization) data.getSerializableExtra(ConfigurationFile.IntentConstants.SPECIALIZATION_DATA);
                btSpecialization.setText(specialization.getName());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void filter(View view){
        cityFlag=false;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View filter = inflater.inflate(R.layout.filter_layout, null);
        btCountry=filter.findViewById(R.id.bt_country);
        btCity=filter.findViewById(R.id.bt_city);
        btSpecialization=filter.findViewById(R.id.bt_specialization);
        builder.setView(filter);
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        btCountry.setOnClickListener(v -> {
            Intent i=new Intent(context, CountryActivity.class);
            ((Activity)context).startActivityForResult(i,ConfigurationFile.IntentConstants.REQUEST_CODE_COUNTRY);
        });
        btCity.setOnClickListener(v -> {
            if(cityFlag) {
                Intent i = new Intent(context, CityActivity.class);
                ((Activity)context).startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_CITY);
            }else {
                callBack.updateUi(ConfigurationFile.Constants.SELECT_COUNTRY);
            }
        });
        btSpecialization.setOnClickListener(v -> {
            Intent i = new Intent(context,SpecializationActivity.class);
            i.putExtra(ConfigurationFile.IntentConstants.TYPE,savedType);
            ((Activity)context).startActivityForResult(i, ConfigurationFile.IntentConstants.REQUEST_CODE_SPECIALIZATION);
        });
        filter.findViewById(R.id.done).setOnClickListener(v -> {
            String spId=(specialization!=null)?String.valueOf(specialization.getId()):null;
            String counId=(country!=null)?String.valueOf(country.getId()):null;
            String citId=(city!=null)?String.valueOf(city.getId()):null;
                search("0",counId,citId,spId,savedType);
            dialog.dismiss();
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void back(View view){
        ((Activity)context).finish();
    }
}
