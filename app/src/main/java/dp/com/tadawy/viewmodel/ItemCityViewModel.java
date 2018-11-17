package dp.com.tadawy.viewmodel;

import android.content.Context;
import android.view.View;
import dp.com.tadawy.pojo.model.City;
import dp.com.tadawy.view.callback.CityCallback;

public class ItemCityViewModel {
    private Context context;
    private City city;
    private CityCallback cityCallback;

    public ItemCityViewModel(Context context, City city, CityCallback cityCallback) {
        this.context = context;
        this.city = city;
        this.cityCallback=cityCallback;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getName(){
        return city.getName();
    }

    public void onItemClick(View v){
        cityCallback.getCities(city);
    }
}
