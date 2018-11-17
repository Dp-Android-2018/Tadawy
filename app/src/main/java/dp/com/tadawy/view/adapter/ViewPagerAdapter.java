package dp.com.tadawy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import dp.com.tadawy.R;
import dp.com.tadawy.view.activity.DataActivity;

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    List<Integer> imags;
    LayoutInflater inflater;
    int flag;

    public ViewPagerAdapter(Activity activity, List<Integer> imags,int flag) {
        this.activity = activity;
        this.imags = imags;
        this.flag=flag;
    }

    @Override
    public int getCount() {
        return imags.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=(LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.view_pager_item,container,false);

        ImageView imageView=view.findViewById(R.id.image);
        DisplayMetrics dis=new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);

        int heigt=dis.heightPixels;
        int width =dis.widthPixels;
        imageView.setMinimumHeight(heigt);
        imageView.setMinimumWidth(width);

        imageView.setImageResource(imags.get(position));
        container.addView(view);

        if(flag==1) {
            view.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt("page", position);
                Intent intent = new Intent(activity, DataActivity.class);
                intent.putExtras(bundle);
                // intent.putExtra("page",position);
                activity.startActivity(intent);

            });
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }
}
