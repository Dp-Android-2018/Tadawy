package dp.com.tadawy.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.FragmentNotificationBinding;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.NotificationViewModel;

public class NotificationFragment extends Fragment implements BaseInterface {
    private NotificationViewModel viewModel;
    private FragmentNotificationBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel=new NotificationViewModel(getContext(),this,"");
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_notification,container,false);
        binding.setViewModel(viewModel);
        binding.rvReservation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        View view=binding.getRoot();
        return view;
    }

    @Override
    public void updateUi(int code) {

    }
}
