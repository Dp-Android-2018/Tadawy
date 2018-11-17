package dp.com.tadawy.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.FragmentSignupBinding;
import dp.com.tadawy.viewmodel.SignupViewModel;


public class SignupFragment extends Fragment {
    private SignupViewModel signupViewModel;
    private FragmentSignupBinding signupBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        signupViewModel=new SignupViewModel(getContext());
        signupBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_signup,container,false);
        signupBinding.setSignupViewModel(signupViewModel);
        View view=signupBinding.getRoot();
        return view;
    }
}
