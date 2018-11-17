package dp.com.tadawy.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.FragmentMainTabBinding;
import dp.com.tadawy.view.activity.DataActivity;
import dp.com.tadawy.view.adapter.ViewPagerAdapter;
import dp.com.tadawy.viewmodel.MainTabViewModel;
import me.crosswall.lib.coverflow.CoverFlow;

public class MainTabFragment extends Fragment {
    private MainTabViewModel mainTabViewModel;
    private FragmentMainTabBinding mainTabBinding;
    List<Integer> images=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainTabViewModel=new MainTabViewModel(getActivity());
        mainTabBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_main_tab,container,false);
        mainTabBinding.setMainViewModel(mainTabViewModel);
        images.add(R.drawable.clinic_main);
        images.add(R.drawable.spa_main);
        images.add(R.drawable.hospital_main);
        images.add(R.drawable.center_main);
        initViewPager();
        View view=mainTabBinding.getRoot();
        return view;
    }

    public void initViewPager(){
        mainTabBinding.pagerContainer.setOverlapEnabled(true);
        final ViewPager viewPager=mainTabBinding.pagerContainer.getViewPager();
        ViewPagerAdapter adapter=new ViewPagerAdapter(getActivity(),images,1);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);

        new CoverFlow.Builder().with(viewPager)
                .scale(0.3f)
                .pagerMargin(-80)
                .spaceSize(0f)
                .build();
    }
}
