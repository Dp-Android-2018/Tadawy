package dp.com.tadawy.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dp.com.tadawy.R;
import dp.com.tadawy.databinding.ActivityDataBinding;
import dp.com.tadawy.utils.ConfigurationFile;
import dp.com.tadawy.view.adapter.ViewPagerAdapter;
import dp.com.tadawy.view.callback.BaseInterface;
import dp.com.tadawy.viewmodel.ClinicDataViewModel;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;

public class DataActivity extends BaseActivity implements BaseInterface {

    private ClinicDataViewModel viewModel;
    private ActivityDataBinding binding;

    List<Integer> images=new ArrayList<>();

    int x;
    String type;
    public DataActivity() {
        System.out.println("Intent value is : "+x);
        images.add(R.drawable.clinic_data);
        images.add(R.drawable.spa_data);
        images.add(R.drawable.hospital_data);
        images.add(R.drawable.center_data);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        x=(Integer)bundle.get("page");

        viewModel=new ClinicDataViewModel(DataActivity.this,this,x);
        binding= DataBindingUtil.setContentView(DataActivity.this,R.layout.activity_data);
        binding.setViewModel(viewModel);
        binding.rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.rvGrid.setLayoutManager(new GridLayoutManager(this,2));
        PagerContainer container = binding.pagerContainer;//findViewById(R.id.pager_container);
        final ViewPager pager = container.getViewPager();
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(DataActivity.this,images,0);
        //navigationFragments(new ClinicDataFragment());
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(pagerAdapter.getCount());
        pager.setClipChildren(false);
        new CoverFlow.Builder().with(pager)
                .scale(0.3f)
                .pagerMargin(-80)
                .spaceSize(0f)
                .build();
        pager.setCurrentItem(x);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int index =pager.getCurrentItem();
            @Override
            public void onPageSelected(int position) {
                index = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                int width = frameLayout.getWidth();
//                frameLayout.scrollTo((int) (width * position + width * positionOffset), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("State is : "+state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    System.out.println("index is : "+index);
                    if(index==0){
                        viewModel.title.set("جميع العيادات");
                        viewModel.search("0",null,null,null,ConfigurationFile.Constants.CLINIC);
                    }else if(index==1){
                        viewModel.title.set("جميع المنتجعات");
                        viewModel.search("0",null,null,null,ConfigurationFile.Constants.SPA);
                    }
                    if(index==2){
                        viewModel.title.set("جميع المستشفيات");
                        viewModel.search("0",null,null,null,ConfigurationFile.Constants.HOSPITAL);
                    }else if(index==3){
                        viewModel.title.set("جميع المراكز الصحيه");
                        viewModel.search("0",null,null,null,ConfigurationFile.Constants.FIRM);
                    }

                    //bindingPager.setCurrentItem(index);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        viewModel.onActivityResult(requestCode,resultCode,data);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void updateUi(int code) {
        switch (code){
            case ConfigurationFile.Constants.SELECT_COUNTRY:{
                Snackbar.make(binding.clRoot,R.string.select_country,Snackbar.LENGTH_LONG).show();
                break;
            }
        }
    }
}
