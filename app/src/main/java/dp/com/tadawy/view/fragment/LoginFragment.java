package dp.com.tadawy.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.FragmentLoginBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.utils.CustomUtils;
import dp.com.tadawy.view.activity.ActivationActivity;
import dp.com.tadawy.view.activity.ConnectTeamWorkActivity;
import dp.com.tadawy.view.activity.ContainerActivity;
import dp.com.tadawy.view.activity.MainActivity;
import dp.com.tadawy.view.activity.RequestActivity;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment implements BaseInterface {
    private LoginViewModel loginViewModel;
    private FragmentLoginBinding loginBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginViewModel=new LoginViewModel(getActivity(),this);
        loginBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
        loginBinding.setLoginViewModel(loginViewModel);
        View view=loginBinding.getRoot();
        return view;
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.SUCCESS_CODE:{

                break;
            }
            case ConfigurationFile.Constants.FILL_ALL_DATA_ERROR:{
                Snackbar.make(loginBinding.clRoot,R.string.fill_data,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.INVALED_EMAIL:{
                Snackbar.make(loginBinding.clRoot,R.string.invaled_mail,Snackbar.LENGTH_LONG).show();
                break;
            }
            case ConfigurationFile.Constants.SUSBENDED:{
                Snackbar.make(loginBinding.clRoot,getString(R.string.try_later),Snackbar.LENGTH_LONG).show();
            }
            case ConfigurationFile.Constants.INVALED_EMAIL_PASSWORD:{
                Snackbar.make(loginBinding.clRoot,R.string.invaled_mail_password,Snackbar.LENGTH_LONG).show();
                break;
            }

        }

    }
}
