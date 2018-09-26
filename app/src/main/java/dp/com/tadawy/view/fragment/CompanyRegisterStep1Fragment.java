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
import dp.com.tadawy.databinding.FragmentCompanyRegisterStep1Binding;
import dp.com.tadawy.viewmodel.CompanyRegisterStep1ViewModel;

public class CompanyRegisterStep1Fragment extends Fragment {
    private CompanyRegisterStep1ViewModel step1ViewModel;
    private FragmentCompanyRegisterStep1Binding step1Binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        step1ViewModel=new CompanyRegisterStep1ViewModel();
        step1Binding= DataBindingUtil.inflate(inflater, R.layout.fragment_company_register_step1,container,false);
        step1Binding.setStep1ViewModel(step1ViewModel);
        View view=step1Binding.getRoot();
        return view;
    }
}
