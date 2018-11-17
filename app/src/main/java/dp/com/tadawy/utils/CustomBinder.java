package dp.com.tadawy.utils;


import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableList;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import dp.com.tadawy.R;
import dp.com.tadawy.pojo.model.CommentItem;
import dp.com.tadawy.pojo.model.Country;
import dp.com.tadawy.pojo.model.LoginResponseContent;
import dp.com.tadawy.pojo.model.ReservationContent;
import dp.com.tadawy.view.adapter.AddImageAdapter;
import dp.com.tadawy.view.adapter.CommentsAdapter;
import dp.com.tadawy.view.adapter.CountryRadioAdapter;
import dp.com.tadawy.view.adapter.ListCompaniesAdapter;
import dp.com.tadawy.view.adapter.ReservationAdapter;
import dp.com.tadawy.view.adapter.SearchAdapter;
import dp.com.tadawy.view.adapter.SearchGridAdapter;

/**
 * Created by DELL on 20/03/2018.
 */

public class CustomBinder {

    @BindingAdapter({"bind:bitmap"})
    public static void setbitmap(ImageView imageView, Bitmap bitmap){
        if (bitmap!=null)
            imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter({"bind:recyclerListener"})
    public static void onrecyclerListener(RecyclerView view, RecyclerView.OnScrollListener listener){
        view.addOnScrollListener(listener);
    }

    @BindingAdapter({"bind:clinicInfo"})
    public static void setRecyclerCompanies(RecyclerView view, ObservableList<LoginResponseContent> searchResponse) {
        SearchAdapter adapter=new SearchAdapter(searchResponse);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:clinicInfoGrid"})
    public static void setRecyclerCompaniesGrid(RecyclerView view, ObservableList<LoginResponseContent> searchResponse) {
        SearchGridAdapter adapter=new SearchGridAdapter(searchResponse);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url){
        System.out.println("image url : "+url);
        if (url!=null && !url.equals(""))
            Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.tadawy_logo).into(imageView);
    }

    @BindingAdapter({"bind:radioCountries"})
    public static void setRadioCountries(RecyclerView view, ObservableList<Country> searchResponse) {
        CountryRadioAdapter adapter=new CountryRadioAdapter(searchResponse);
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:comments"})
    public static void setComments(RecyclerView view,ObservableList<CommentItem> comments) {

        CommentsAdapter adapter=new CommentsAdapter(comments);
            System.out.println("size of comment list in custom binder "+comments.size());
        view.setAdapter(adapter);
    }
    @BindingAdapter({"bind:reservation","bind:status"})
    public static void setReservation(RecyclerView view,ObservableList<ReservationContent> reservations,String status) {

        ReservationAdapter adapter=new ReservationAdapter(reservations,status);
        System.out.println("size of comment list in custom binder "+reservations.size());
        view.setAdapter(adapter);
    }

    @BindingAdapter({"bind:listview"})
    public static void setList(RecyclerView view, ObservableList<String> items){
        ListCompaniesAdapter adapter=new ListCompaniesAdapter(items);
        view.setAdapter(adapter);
    }
    @BindingAdapter({"bind:addprojectrecycler"})
    public static void setAddprojectAdapter(RecyclerView view,ObservableList<String>imageLinks) {
        AddImageAdapter adapter=new AddImageAdapter(imageLinks);
        if (imageLinks!=null)
            System.out.println("size of list in custom binder"+imageLinks.size());
        else
            System.out.println("project is null in custom binder :(");
        view.setAdapter(adapter);
    }


}
