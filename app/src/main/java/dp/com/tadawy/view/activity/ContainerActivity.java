package dp.com.tadawy.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.roughike.bottombar.OnTabSelectListener;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityContainerBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.fragment.MainTabFragment;
import dp.com.tadawy.view.fragment.NotificationFragment;
import dp.com.tadawy.view.fragment.ProfileFragment;
import dp.com.tadawy.view.fragment.SettingsFragment;
import dp.com.tadawy.viewmodel.ContainerViewModel;

public class ContainerActivity extends BaseActivity {
    private ContainerViewModel containerViewModel;
    private ActivityContainerBinding containerBinding;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        containerViewModel=new ContainerViewModel();
        containerBinding= DataBindingUtil.setContentView(ContainerActivity.this,R.layout.activity_container);
        containerBinding.setContainerViewModel(containerViewModel);
        containerBinding.bottomBar.setDefaultTab(R.id.main_tap);
        int i=getIntent().getIntExtra(ConfigurationFile.IntentConstants.CONTAINER_RESERVATION,0);
        if(i==1){
            containerBinding.bottomBar.setDefaultTab(R.id.reservation);
        }
        bottombar();
    }
    public void bottombar(){
        containerBinding.bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch(tabId){
                    case R.id.settings:{
                        SettingsFragment settingsFragment=new SettingsFragment();
                        navigationFragments(settingsFragment);
                        break;
                    }
                    case R.id.profile:{
                        ProfileFragment profileFragment=new ProfileFragment();
                        navigationFragments(profileFragment);
                        break;
                    }
                    case R.id.reservation:{
                        NotificationFragment notificationFragment=new NotificationFragment();
                        navigationFragments(notificationFragment);
                        break;
                    }
                    case R.id.main_tap:{
                        MainTabFragment mainTabFragment=new MainTabFragment();
                        navigationFragments(mainTabFragment);
                        break;
                    }
                }
            }
        });
    }

    public void navigationFragments(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(CustomUtils.getInstance().getSaveUserObject(getApplicationContext())!=null) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Snackbar.make(containerBinding.clRoot, "إضغط مره اخرى للخروج", Snackbar.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }
}
