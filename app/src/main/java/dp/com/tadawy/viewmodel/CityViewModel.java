package dp.com.tadawy.viewmodel;

import android.content.Context;

import dp.com.tadawy.view.callback.BaseInterface;

public class CityViewModel  {

    Context context;
    private BaseInterface callback;

    public CityViewModel(Context context, BaseInterface callback) {
        this.context = context;
        this.callback=callback;
    }
}
