package dp.com.tadawy.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class ActionBarViewModel {

    Context context;

    public ActionBarViewModel(Context context) {
        this.context = context;
    }

    public void back(View view){
        ((Activity)context).finish();
    }
}
