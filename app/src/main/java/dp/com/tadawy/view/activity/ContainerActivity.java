package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.OnTabSelectListener;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityContainerBinding;
import dp.com.tadawy.viewmodel.ContainerViewModel;

public class ContainerActivity extends AppCompatActivity {
    private ContainerViewModel containerViewModel;
    private ActivityContainerBinding containerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerViewModel=new ContainerViewModel();
        containerBinding= DataBindingUtil.setContentView(ContainerActivity.this,R.layout.activity_container);
        containerBinding.setContainerViewModel(containerViewModel);
    }


  /*  public void bottombar(){
        containerBinding.bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch(tabId){
                    case R.id.settings:{
                        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null) {
                            Fragment setting;
                            if (CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().
                                    equals(ConfigurationFile.Constants.CLIENT)) {
                                setting = new ClientSettingFragment();
                                navigationFragments(setting);
                            } else if (CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().
                                    equals(ConfigurationFile.Constants.COMPANY)&& CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getStatus().equals("true")) {
                                setting = new CompanyProfileFragment();
                                navigationFragments(setting);
                            }else {
                                Snackbar.make(containerBinding.drawer, "حسابك غير مفعل ", Snackbar.LENGTH_LONG).show();
                            }
                        }else{
                            Snackbar.make(containerBinding.drawer, "انت غير مسجل ", Snackbar.LENGTH_LONG).show();
                        }
                        break;
                    }
                    case R.id.companies:{
                        Fragment companies=new CompaniesGridFragment();
                        navigationFragments(companies);
                        break;
                    }
                    case R.id.add_advert:{
                        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null&&
                                CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getRole().equals(ConfigurationFile.Constants.COMPANY)&&
                                CustomUtils.getInstance().getSaveUserObject(getApplicationContext()).getStatus().equals("true")) {
                            Fragment addAdvert = new AddAdvertFragment();
                            navigationFragments(addAdvert);
                        }else{
                            Snackbar.make(containerBinding.drawer, "إضافة إعلان غير متاحه لك", Snackbar.LENGTH_LONG).show();
                        }
                        break;
                    }
                    case R.id.search:{
                        navigationFragments(home);
                        break;
                    }
                    case R.id.adverts:{
                        Fragment advert=new ShowAdvertsFragment();
                        navigationFragments(advert);
                        break;
                    }
                    default:{
                        Snackbar.make(containerBinding.drawer, "مرحبا بك فى عمار", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }*/
}
