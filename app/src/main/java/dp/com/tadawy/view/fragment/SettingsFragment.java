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
import dp.com.tadawy.databinding.FragmentSettingsBinding;
import dp.com.tadawy.viewmodel.SettingsViewModel;

public class SettingsFragment extends Fragment {

    private SettingsViewModel viewModel;
    private FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel=new SettingsViewModel(getContext());
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_settings,container,false);
        binding.setViewModel(viewModel);
        View view=binding.getRoot();
        return view;
    }
}
