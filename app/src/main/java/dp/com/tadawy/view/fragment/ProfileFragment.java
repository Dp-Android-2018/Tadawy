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
import dp.com.tadawy.databinding.FragmentProfileBinding;
import dp.com.tadawy.viewmodel.ProfileViewModel;

public class ProfileFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel=new ProfileViewModel(getContext());
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);
        binding.setViewModel(profileViewModel);
        View view=binding.getRoot();
        return view;
    }
}
