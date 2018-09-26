package dp.com.tadawy.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.FragmentLoginBinding;
import dp.com.tadawy.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private FragmentLoginBinding loginBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loginViewModel=  ViewModelProviders
                .of(this).get(LoginViewModel.class);
        loginBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false);
        loginBinding.setLoginViewModel(loginViewModel);
        View view=loginBinding.getRoot();
        return view;
    }
}
