package dp.com.tadawy.viewmodel;

import android.content.Context;

public class ListItemStringViewModel {
    public String companyName;
    Context context;

    public ListItemStringViewModel(String companyName, Context context) {
        this.companyName = companyName;
        this.context = context;
    }

    public void setCompanyName(String  companyName) {
        this.companyName = companyName;
    }
}
